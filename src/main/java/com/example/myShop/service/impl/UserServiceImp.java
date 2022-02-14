package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.exception.OrderDeleteException;
import com.example.myShop.domain.exception.UserNotFoundException;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.repository.UserRepository;
import com.example.myShop.service.UserService;
import com.example.myShop.utils.InitProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User get(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User getAndInitialize(Integer id){
        return Optional.of(id)
                .map(userRepository::findById)
                .get()
                .map(InitProxy::initUser)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Page<User> getAndInitializeAll(Pageable pageable){
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> list = new ArrayList<>();

        for(User user : userPage){
            list.add(InitProxy.initUser(user));
        }

        return new PageImpl<>(list);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Integer id) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> userMapper.merge(current, user))
                .map(userRepository::save)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void delete(Integer userId){
        User user = get(userId);

        List<Order> orderList = user.getOrders();
        // Checking for active order
        for(Order order : orderList){
            if(order.isOrderActive()){
                throw new OrderDeleteException("Delete operation is not acceptable for status: "
                        + order.getOrderStatus() +
                        ". Status must be CANCELED or COMPLETED" +
                        "User id: " + userId + ". Order id: " + order.getId());
            }
        }

        userRepository.delete(user);
    }
}

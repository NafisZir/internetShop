package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Order;
import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.exception.OrderDeleteException;
import com.example.myShop.domain.exception.UserNotFoundException;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.repository.UserRepository;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public User getAndInitialize(Integer id){
        User result = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        Hibernate.initialize(result);
        Hibernate.initialize(result.getOrders());
        Hibernate.initialize(result.getBankCards());
        return result;
    }

    @Override
    public Map<String, Object> getAndInitializeAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        for(User user : userPage){
            Hibernate.initialize(user);
            Hibernate.initialize(user.getOrders());
            Hibernate.initialize(user.getBankCards());
        }

        Map<String, Object> response = new HashMap<>();

        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return response;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Integer id) {
        return Optional.of(id)
                .map(this::getAndInitialize)
                .map(current -> userMapper.merge(current, user))
                .map(userRepository::save)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void delete(Integer userId){
        User user = getAndInitialize(userId);

        List<Order> orderList = user.getOrders();
        // Checking for active order
        for(Order order : orderList){
            if(order.isOrderActive()){
                throw new OrderDeleteException("Delete operation is not acceptable for status: "
                        + order.getOrderStatus().getStatus() +
                        ". Status must be CANCELED or COMPLETED" +
                        "User id: " + userId + ". Order id: " + order.getId());
            }
        }

        userRepository.deleteById(userId);
    }
}

package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.User;
import com.example.myShop.domain.exception.UserNotFoundException;
import com.example.myShop.domain.mapper.UserMapper;
import com.example.myShop.repository.UserRepository;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User get(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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
    public void delete(Integer id){
        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}

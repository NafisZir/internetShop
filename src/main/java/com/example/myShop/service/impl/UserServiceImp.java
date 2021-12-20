package com.example.myShop.service.impl;

import com.example.myShop.domain.entity.Role;
import com.example.myShop.domain.entity.User;
import com.example.myShop.repository.ClientRepository;
import com.example.myShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * @author nafis
 * @since 19.12.2021
 */

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User get(Integer id){
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public boolean create(User user) {
        String clientEmail = user.getEmail();
        if (clientRepository.findByEmail(clientEmail) != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        clientRepository.save(user);
        return true;
    }

    @Override
    public void update(User user){ clientRepository.save(user); }

    @Override
    public void delete(Integer id){
        clientRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers(){
        return clientRepository.findAll();
    }

    @Override
    public User getUserByPrincipal(Principal principal){
        if(principal == null) return new User();
        return clientRepository.findByEmail(principal.getName());
    }
}

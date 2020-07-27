package com.shopping.shoppingCartApp.service;

import com.shopping.shoppingCartApp.models.User;
import com.shopping.shoppingCartApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserDetailByUserName(final String userName){
        return userRepository.findByUserName(userName);
    }

    public User saveUser(final User user){
        return userRepository.save(user);
    }
}

package com.shopping.shoppingCartApp.controllers;

import com.shopping.shoppingCartApp.models.User;
import com.shopping.shoppingCartApp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    UserService userService;

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return userList;
    }
}

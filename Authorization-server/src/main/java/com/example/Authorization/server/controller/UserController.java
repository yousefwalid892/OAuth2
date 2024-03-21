package com.example.Authorization.server.controller;

import com.example.Authorization.server.entities.User;
import com.example.Authorization.server.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userDetailsService.createUser(user);
    }
}

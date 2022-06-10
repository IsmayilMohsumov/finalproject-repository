package com.example.finalproject.controller;

import com.example.finalproject.entity.User;
import com.example.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UseRestController {
    private final UserService userService;

    @GetMapping("/get")
    public List<User> getAll(){
        return userService.getAll();
    }
}

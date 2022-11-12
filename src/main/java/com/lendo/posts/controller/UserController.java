package com.lendo.posts.controller;

import com.lendo.posts.dto.PostDto;
import com.lendo.posts.dto.UserDto;
import com.lendo.posts.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{userId}/posts")
    public List<PostDto> getAllUserPosts(@PathVariable long userId) {
        return userService.getAllUserPosts(userId);
    }

}

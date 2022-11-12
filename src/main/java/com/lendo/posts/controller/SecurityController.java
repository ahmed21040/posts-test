package com.lendo.posts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class SecurityController {


    @RequestMapping("/validateUser")
    public Principal user(Principal user) {
        return user;
    }
}

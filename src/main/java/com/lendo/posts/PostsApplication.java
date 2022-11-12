package com.lendo.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableFeignClients
@EnableOAuth2Client
public class PostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsApplication.class, args);
    }

}

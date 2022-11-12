package com.lendo.posts.client;


import com.lendo.posts.dto.PostDto;
import com.lendo.posts.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UserFeignClient", url = "${client.url}")
public interface UserFeignClient {
    @GetMapping("/users")
    List<UserDto> getAllUsers( );


    @GetMapping("/users/{userId}/posts")
    List<PostDto> getAllUserPosts(@PathVariable long userId);
}

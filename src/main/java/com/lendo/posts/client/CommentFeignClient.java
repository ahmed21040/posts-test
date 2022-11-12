package com.lendo.posts.client;


import com.lendo.posts.dto.CommentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "CommentFeignClient", url = "${client.url}")
public interface CommentFeignClient {
    @GetMapping("/comments")
    List<CommentDto> getAllComments( );



}

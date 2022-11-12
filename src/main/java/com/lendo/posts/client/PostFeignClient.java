package com.lendo.posts.client;


import com.lendo.posts.dto.CommentDto;
import com.lendo.posts.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PostFeignClient", url = "${client.url}")
public interface PostFeignClient {
    @GetMapping("/posts")
    List<PostDto> getAllPosts( );


    @GetMapping("/posts/{postId}/comments")
    List<CommentDto> getAllPostComments(@PathVariable long postId);
}

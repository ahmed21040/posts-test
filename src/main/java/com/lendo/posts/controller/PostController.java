package com.lendo.posts.controller;

import com.lendo.posts.dto.CommentDto;
import com.lendo.posts.dto.PostDto;
import com.lendo.posts.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {

    @Autowired
    PostService  postService;

    @GetMapping("")
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }


    @GetMapping("/{postId}/comments")
    public List<CommentDto> getAllPostComments(@PathVariable long postId) {
        return postService.getAllPostComments(postId);
    }

}

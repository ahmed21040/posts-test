package com.lendo.posts.controller;

import com.lendo.posts.dto.CommentDto;
import com.lendo.posts.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Slf4j
public class CommentController {

    @Autowired
    CommentService  commentService;

    @GetMapping("")
    public List<CommentDto> getAllComments() {
        return commentService.getAllComments();
    }



}

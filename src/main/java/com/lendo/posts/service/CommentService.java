package com.lendo.posts.service;

import com.lendo.posts.client.CommentFeignClient;
import com.lendo.posts.dto.CommentDto;
import com.lendo.posts.exception_handling.CommentsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentFeignClient commentFeignClient;

    public List<CommentDto> getAllComments() {
        try {
            return commentFeignClient.getAllComments();
        } catch (Exception e) {
            throw new CommentsNotFoundException();
        }
    }

 }

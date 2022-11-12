package com.lendo.posts.service;

import com.lendo.posts.client.PostFeignClient;
import com.lendo.posts.dto.CommentDto;
import com.lendo.posts.dto.PostDto;
import com.lendo.posts.exception_handling.CommentsNotFoundException;
import com.lendo.posts.exception_handling.PostsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    PostFeignClient postFeignClient;

    public List<PostDto> getAllPosts() {
        try {
            return postFeignClient.getAllPosts();
        } catch (Exception e){
        throw  new PostsNotFoundException();
    }
    }

    public List<CommentDto> getAllPostComments(long postId) {
        try{
            return postFeignClient.getAllPostComments(postId);
        }catch (Exception e){
            throw  new CommentsNotFoundException();
        }
    }
}

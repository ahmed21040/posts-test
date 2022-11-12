package com.lendo.posts.service;

import com.lendo.posts.client.PostFeignClient;
import com.lendo.posts.dto.PostDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {


    @Mock
    private PostFeignClient postFeignClient;
    @InjectMocks
    private PostService postService;


    @Test
    public void getAllPosts_test() {
        long id = 123;

        when(postFeignClient.getAllPosts()).thenReturn(List.of(PostDto.builder().id(id).build()));

        List<PostDto> postsList = postService.getAllPosts();

        verify(postFeignClient).getAllPosts();
        Assert.assertEquals(1, postsList.size());
        Assert.assertEquals(id, postsList.get(0).getId());

    }
}

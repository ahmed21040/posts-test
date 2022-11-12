package com.lendo.posts.service;

import com.lendo.posts.client.CommentFeignClient;
import com.lendo.posts.dto.CommentDto;
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
public class CommentServiceTest {

    @Mock
    private CommentFeignClient commentFeignClient;
    @InjectMocks
    private CommentService commentService;


    @Test
    public void getAllComments_test() {

        long id = 123;

        when(commentFeignClient.getAllComments()).thenReturn(List.of(CommentDto.builder().id(id).build()));

        List<CommentDto> commentsList = commentService.getAllComments();

        Assert.assertEquals(1, commentsList.size());
        Assert.assertEquals(id, commentsList.get(0).getId());

    }
}

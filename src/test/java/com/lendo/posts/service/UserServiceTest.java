package com.lendo.posts.service;

import com.lendo.posts.client.UserFeignClient;
import com.lendo.posts.dto.PostDto;
import com.lendo.posts.dto.UserDto;
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
public class UserServiceTest {


    @Mock
    private UserFeignClient userFeignClient;
    @InjectMocks
    private UserService userService;


    @Test
    public void getAllUsers_test() {

        long id = 123;

        when(userFeignClient.getAllUsers()).thenReturn(List.of(UserDto.builder().id(id).build()));

        List<UserDto> userList = userService.getAllUsers();

        verify(userFeignClient).getAllUsers();
        Assert.assertEquals(1, userList.size());
        Assert.assertEquals(id, userList.get(0).getId());
    }
}

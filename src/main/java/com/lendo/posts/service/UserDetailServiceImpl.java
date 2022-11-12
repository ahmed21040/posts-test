package com.lendo.posts.service;

import com.lendo.posts.config.AuthUserDetail;
import com.lendo.posts.config.User;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        System.out.println("Username service " + name);
        User user = User.builder()
                .username("admin")
                .email("admin@lendo.com")
                .build();

        UserDetails userDetails = new AuthUserDetail(user);
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;


    }
}
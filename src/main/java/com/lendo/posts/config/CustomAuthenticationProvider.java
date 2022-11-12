package com.lendo.posts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    //	@Autowired
    PasswordEncoder passwordEncoder;

    @Value("${auth.token.name}")
    private String name;

    @Value("${auth.token.pass}")
    private String pass;

    @Autowired
    public CustomAuthenticationProvider(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (!(username.equals(name) && password.equals(pass))) {
            throw new UsernameNotFoundException("Username or password wrong");
        }
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        return new UsernamePasswordAuthenticationToken(username, password, authorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
package com.lendo.posts.config;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AuthUserDetail extends User implements UserDetails {

    public AuthUserDetail(User user) {
        super(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return false;
    }


}
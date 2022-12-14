package com.lendo.posts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);


    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers( "/test")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and().logout().clearAuthentication(true)
                .deleteCookies().invalidateHttpSession(true);
        http.cors();
    }





    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return createDelegatingPasswordEncoder();
    }

    @Bean
    CustomAuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider(this.passwordEncoder() );
    }

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        encoders.put("SHA-256",
                new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
        encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());

        return new DelegatingPasswordEncoder(encodingId, encoders);
    }


}
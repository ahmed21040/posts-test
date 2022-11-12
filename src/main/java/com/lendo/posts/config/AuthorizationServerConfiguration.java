package com.lendo.posts.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer

public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${app.keystore.name}")
    private String keystore;

    @Value("${app.keystore.pass}")
    private String keystorepass;

    @Value("${app.key.name}")
    private String key;

    @Value("${app.key.pass}")
    private String keypass;

    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("web").accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(600)
                .secret("{noop}secret") // test
                .scopes("READ", "WRITE")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials");

    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();

        endpoints.tokenStore(tokenStore()); // doesn't save tokens
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
        ;

        // attach the token enhancer!
        endpoints.tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(keystore), keystorepass.toCharArray())
                .getKeyPair(key, keypass.toCharArray());
        converter.setKeyPair(keyPair);
        return converter;
    }



    @Bean(name = "jdbcTokenStore")
    public TokenStore tokenStore() {
        return new CustomInMemoryTokenStore();
    }


    //	@Bean
    public DefaultTokenServices tokenServices() {

        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(enhancerChain);
        return defaultTokenServices;
    }

}
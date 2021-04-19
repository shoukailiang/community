package com.shoukailiang.community.oauth2.config;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.util.Map;

@Configuration
public class JwtTokenStoreConfig {


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("shoukailiang");
        // 将定义的转换器对象添加到jwt转换器中
        converter.setAccessTokenConverter(new CustomAccessTokenConverter());
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 定制 AccessToken 转换器，为额外添加的用户信息在资源服务中获取
     */
    private class CustomAccessTokenConverter extends DefaultAccessTokenConverter {
        @Override
        public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
            OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
            oAuth2Authentication.setDetails(map);
            return oAuth2Authentication;
        }
    }
}
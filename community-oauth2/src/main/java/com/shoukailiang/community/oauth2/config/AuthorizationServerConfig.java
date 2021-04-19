package com.shoukailiang.community.oauth2.config;

import com.shoukailiang.community.oauth2.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer // 标识为认证服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean // 客户端使用jdbc管理
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置被允许访问认证服务的客户端信息：数据库方式
     * 如：门户客户端，后台客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // jdbc管理客户端
        clients.withClientDetails(jdbcClientDetailsService());
    }


    @Autowired // 在SpringSecurityConfig中已经添加到容器中了，密码模式需要
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Resource
    private TokenStore tokenStore;

    // jwt 转换器
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource // 注入增强器
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * 关于认证服务器端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 密码模块必须使用这个authenticationManager实例
        endpoints.authenticationManager(authenticationManager);
        // 刷新令牌需要 使用userDetailsService
        endpoints.userDetailsService(userDetailsService);
        // 令牌管理方式
        endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter);

        // 添加增强器
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        // 组合 增强器和jwt转换器
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);
        // 将认证信息的增强器添加到端点上
        endpoints.tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // /oauth/check_token 解析令牌，默认情况 下拒绝访问
        security.checkTokenAccess("permitAll()");
    }
}
package com.shoukailiang.community.web.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ResultEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired // 负载均衡的client
    private LoadBalancerClient loadBalancerClient;

    /**
     * 通过刷新令牌获取新的认证令牌
     *
     * @param header       请求头：客户端信息，Basic clientId:clientSecret
     * @param refreshToken 刷新令牌
     * @return
     */
    public ResultVO refreshToken(String header, String refreshToken) throws HttpProcessException {
        // 采用客户端负载均衡，从Nacos服务器中获取对应服务的ip与端口号
        ServiceInstance serviceInstance =
                loadBalancerClient.choose("auth-server");
        if (serviceInstance == null) {
            return ResultVOUtil.error(ResultEnum.AUTH_SERVER_NOT_FOUND.getMessage());
        }
        // 请求刷新令牌url
        String refreshTokenUrl =
                serviceInstance.getUri().toString() + "/auth/oauth/token";

        // 封装刷新令牌请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        // 构建配置请求头参数
        Header[] headers = HttpHeader.custom() // 自定义请求
                .contentType(HttpHeader.Headers.APP_FORM_URLENCODED) // 数据类型,auth2要求这个协议
                .authorization(header) // 认证请求头（客户信息），会把传过来的header放在请求头
                .build();
        // 请求配置
        HttpConfig config =
                HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);

        // 发送请求,响应认证信息
        String token = HttpClientUtil.post(config);

        JSONObject jsonToken = JSON.parseObject(token);
        // 如果响应内容中包含了error属性值，则获取新的认证失败。
        if (StringUtils.isNotEmpty(jsonToken.getString("error"))) {
            return ResultVOUtil.build(ResultEnum.TOKEN_PAST);
        }

        return ResultVOUtil.success(jsonToken);
    }

}
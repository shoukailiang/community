package com.shoukailiang.community.web.controller;

import com.google.common.base.Preconditions;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.tools.RequestUtil;
import com.shoukailiang.community.web.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class AuthController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String HEADER_TYPE = "Basic ";

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @GetMapping("/user/refreshToken") // localhost:7001/auth/user/refreshToken?refreshToken=xxxx
    public ResultVO refreshToken(HttpServletRequest request) {
        try {
            // 获取请求中的刷新令牌
            String refreshToken = request.getParameter("refreshToken");

            Preconditions.checkArgument(StringUtils.isNotEmpty(refreshToken), "刷新令牌不能为空");

            // 获取请求头
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith(HEADER_TYPE)) {
                throw new UnsupportedOperationException("请求头中无client信息");
            }
            // 解析请求头的客户端信息
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            // 查询客户端信息，核对是否有效
            ClientDetails clientDetails =
                    clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails == null) {
                throw new UnsupportedOperationException("clientId对应的配置信息不存在：" + clientId);
            }
            // 校验客户端密码是否有效
            if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnsupportedOperationException("无效clientSecret");
            }

            // 获取新的认证信息
            return authService.refreshToken(header, refreshToken);
        } catch (Exception e) {
            logger.error("refreshToken={}", e.getMessage(), e);
            return ResultVOUtil.error("新令牌获取失败：" + e.getMessage());
        }


    }

}
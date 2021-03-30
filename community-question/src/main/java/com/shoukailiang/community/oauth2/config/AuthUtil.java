package com.shoukailiang.community.oauth2.config;

import com.shoukailiang.community.entities.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Map;

/**
 * 获取用户信息的util
 */
public class AuthUtil {

    public static SysUser getUserInfo() {
        // 从security上下文中获取认证信息
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationDetails details =
                (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> map = (Map<String, Object>) details.getDecodedDetails();
        Map<String, String>  userInfo = (Map<String, String>) map.get("userInfo");

        SysUser user = new SysUser();
        user.setId(userInfo.get("uid"));
        user.setNickName(userInfo.get("nickName"));
        user.setUsername( userInfo.get("username") );
        user.setEmail( userInfo.get("email") );
        user.setImageUrl( userInfo.get("imageUrl") );
        user.setMobile( userInfo.get("mobile"));

        return user;
    }
}

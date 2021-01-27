package com.shoukailiang.community.util.tools;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestUtil {

    public static String[] extractAndDecodeHeader(String header) throws IOException {
        // `Basic ` 后面开始截取 clientId:clientSecret
        byte[] base64Token = header.trim().substring(6).getBytes(StandardCharsets.UTF_8);

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var8) {
            throw new RuntimeException("请求头解析失败：" + header);
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new RuntimeException("请求头无效：" + token);
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}

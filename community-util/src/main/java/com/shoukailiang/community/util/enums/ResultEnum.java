package com.shoukailiang.community.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(20000, "成功"),

    ERROR(999, "错误"),

    UNAUTHENTICATED(401, "请先通过身份认证"),
    AUTH_FAIL(1400, "认证失败"),

    // token异常
    TOKEN_PAST(1401, "缺少身份凭证信息"),
    TOKEN_ERROR(1402, "令牌错误"),

    HEADEA_ERROR(1403, "请求头错误"),

    AUTH_USERNAME_NONE(1405, "用户名不能为空"),
    AUTH_PASSWORD_NONE(1406, "密码不能为空"),

    AUTH_SERVER_NOT_FOUND(1407, "未找到有效认证服务器，请稍后重试"),

    PERMISSION_NO(306, "您没此权限，快走吧！！！"),
    NOT_ROLE(307, "没有这个角色，非法操作！"),
    NOT_USER(308, "没有这个后台用户，非法操作！"),


    NO_USER(309, "此用户不存在"),

    USERNAME_ERROR(310,"用户名不能为空")


    ;

    private Integer code;
    private String message;
}

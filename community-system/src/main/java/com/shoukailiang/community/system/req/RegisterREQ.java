package com.shoukailiang.community.system.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("注册用户信息请求类")
public class RegisterREQ implements Serializable {
    private static final long serialVersionUID = 3719848921379703158L;
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "确认密码", required = true)
    private String repPassword;
}

package com.shoukailiang.community.system.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel("校验密码请求参数")
public class SysUserCheckPasswordREQ implements Serializable {
    private static final long serialVersionUID = 7891717372226386555L;
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
}

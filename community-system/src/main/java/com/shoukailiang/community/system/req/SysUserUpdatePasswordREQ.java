package com.shoukailiang.community.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("密码修改请求参数")
public class SysUserUpdatePasswordREQ extends SysUserCheckPasswordREQ {

    private static final long serialVersionUID = -6824648116346172402L;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;

    @ApiModelProperty(value = "确认密码", required = true)
    private String repPassword;
}

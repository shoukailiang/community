package com.shoukailiang.community.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUserDTO", description="SysUserDTO")
public class SysUserDTO {
    @ApiModelProperty(value = "用户 ID")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码，加密存储, admin/1234")
    private String password;

    @ApiModelProperty(value = "帐户是否过期(1 未过期，0已过期)")
    private Integer isAccountNonExpired;

    @ApiModelProperty(value = "帐户是否被锁定(1 未过期，0已过期)")
    private Integer isAccountNonLocked;

    @ApiModelProperty(value = "密码是否过期(1 未过期，0已过期)")
    private Integer isCredentialsNonExpired;

    @ApiModelProperty(value = "帐户是否可用(1 可用，0 删除用户)")
    private Integer isEnabled;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "个人简介")
    private String introduce;

    @ApiModelProperty(value = "头衔（0：大众，1：高级讲师，2：首席讲师）")
    private Integer level;

    @ApiModelProperty(value = "头像url")
    private String imageUrl;

    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    @ApiModelProperty(value = "密码更新时间")
    private LocalDateTime pwdUpdateDate;
}

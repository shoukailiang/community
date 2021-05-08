package com.shoukailiang.community.system.req;


import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel(value = "SysUserREQ对象", description = "用户查询条件")
public class SysUserREQ extends BaseRequest<SysUser> {

    private static final long serialVersionUID = -3343824552557039341L;
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String mobile;
}

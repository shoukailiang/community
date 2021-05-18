package com.shoukailiang.community.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRoleDTO", description="SysRoleDTO")
public class SysRoleDTO {
    @ApiModelProperty(value = "角色 ID")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色说明")
    private String remark;
}

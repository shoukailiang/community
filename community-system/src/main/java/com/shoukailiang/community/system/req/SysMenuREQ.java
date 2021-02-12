package com.shoukailiang.community.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value="SysMenuREQ对象", description="菜单列表查询条件")
// 因为不要分页功能，所以不用继承 BaseRequest
public class SysMenuREQ  {

    @ApiModelProperty(value = "菜单名称")
    private String name;
}

package com.shoukailiang.community.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单信息表
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysMenu对象", description="菜单信息表")
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单 ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "父菜单 ID (0为顶级菜单)")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "类型(1目录，2菜单，3按钮)")
    private Integer type;

    @ApiModelProperty(value = "授权标识符")
    private String code;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


    @ApiModelProperty(value = "子菜单集合")
    @TableField(exist = false)
    private List<SysMenu> children;


}

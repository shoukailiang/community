package com.shoukailiang.community.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("community_label")
@ApiModel(value="Label对象", description="标签表")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


    @ApiModelProperty(value = "分类名称，不是表中的字段，不需要传，只有在search接口中会使用到一次")
    @TableField(exist = false) // 不是表中的字段
    private String categoryName;
}

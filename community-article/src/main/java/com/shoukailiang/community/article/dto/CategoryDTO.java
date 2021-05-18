package com.shoukailiang.community.article.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/17 21:38
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CategoryDTO",description = "CategoryDTO")
public class CategoryDTO {
    /**
     * 主键，分布式id
     */
    @ApiModelProperty(value = "主键，分布式id")
    @TableId(value = "id", type= IdType.ASSIGN_ID)
    private String id;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
}

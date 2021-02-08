package com.shoukailiang.community.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 广告信息表
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("community_advert")
@ApiModel(value="Advert对象", description="广告信息表")
public class Advert implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "广告标题")
    private String title;

    @ApiModelProperty(value = "广告图片")
    private String imageUrl;

    @ApiModelProperty(value = "广告链接")
    private String advertUrl;

    @ApiModelProperty(value = "广告跳转方式（_blank：新窗口打开，_self：当前窗口打开）")
    private String advertTarget;

    @ApiModelProperty(value = "广告位置(1:首页轮播)")
    private Integer position;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer status;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;


}

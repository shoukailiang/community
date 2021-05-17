package com.shoukailiang.community.article.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.shoukailiang.community.entities.Label;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/17 17:19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleDTO对象", description="ArticleDTO对象")
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "发布者用户id")
    private String userId;

    @ApiModelProperty(value = "发布者用户昵称")
    private String nickName;

    @ApiModelProperty(value = "发布者头像url")
    private String userImage;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章简介")
    private String summary;

    @ApiModelProperty(value = "文章主图地址")
    private String imageUrl;

    @ApiModelProperty(value = "md主体内容")
    private String mdContent;

    @ApiModelProperty(value = "html主体内容")
    private String htmlContent;

    @ApiModelProperty(value = "浏览次数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer thumhup;

    @ApiModelProperty(value = "0: 已删除, 1:未审核，2:审核通过，3：审核未通过")
    private Integer status;

    @ApiModelProperty(value = "0：不公开，1：公开")
    private Integer ispublic;

    /**
     * 当前分类下的所有标签的接口
     */
    @ApiModelProperty(value="文章下的标签集合")
    @TableField(exist = false)
    private List<Label> labelList;

    /**
     * 前端拿到ids就能渲染
     */
    @ApiModelProperty(value="文章下的标签ID集合")
    @TableField(exist = false)
    private List<String> labelIds;

}


package com.shoukailiang.community.entities;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 问题信息表
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("community_question")
@ApiModel(value="Question对象", description="问题信息表")
public class Question implements Serializable {

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

    @ApiModelProperty(value = "问题标题")
    private String title;

    @ApiModelProperty(value = "md问题内容")
    private String mdContent;

    @ApiModelProperty(value = "html问题内容")
    private String htmlContent;

    @ApiModelProperty(value = "浏览次数")
    private Integer viewCount;

    @ApiModelProperty(value = "点赞数")
    private Integer thumhup;

    @ApiModelProperty(value = "回复数")
    private Integer reply;

    @ApiModelProperty(value = "状态，0：已删除， 1：未解决，2：已解决")
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "所属标签ID集合")
    @TableField(exist = false)
    private List<String> labelIds;
    @ApiModelProperty(value = "所属标签对象集合")
    @TableField(exist = false)
    private List<Label> labelList;


}

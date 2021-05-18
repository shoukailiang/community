package com.shoukailiang.community.question.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shoukailiang.community.entities.Label;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionDTO {
    @ApiModelProperty(value = "主键")
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

    @ApiModelProperty(value = "当前分类下的所有标签的id")
    private List<String> labelIds;
}

package com.shoukailiang.community.feign.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 在后台管理进行对用户的信息修改时，需要用feign进行对文章数据库和问答数据库进行用户信息的修改
 */
@Data
@AllArgsConstructor
@ApiModel("更新用户信息请求类")
public class UserInfoREQ implements Serializable {
    @ApiModelProperty(value = "用户id", required = true)
    private String userId;
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;
    @ApiModelProperty(value = "用户头像url", required = true)
    private String userImage;
}

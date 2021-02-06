package com.shoukailiang.community.article.req;

import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value="ArticleUserREQ对象", description="获取指定用户文章的查询条件")
public class ArticleUserREQ extends BaseRequest<Article> {
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value="是否公开(0：不公开，1：公开)")
    private Integer isPublic;
}

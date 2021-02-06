package com.shoukailiang.community.article.req;

import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章查询条件属性
 */
@Data
@Accessors(chain = true)
@ApiModel(value="ArticleREQ对象", description="文章查询条件")
public class ArticleREQ extends BaseRequest<Article> {
    @ApiModelProperty(value = "文章标题")
    private String title;
    @ApiModelProperty(value = "0: 已删除, 1:未审核，2:已审核")
    private Integer status;
}

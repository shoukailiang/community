package com.shoukailiang.community.article.req;

import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文章搜索条件属性
 */
@Data
@Accessors(chain = true)
@ApiModel(value="SearchREQ对象", description="文章搜索条件")
public class SearchREQ extends BaseRequest<Article> {
    @ApiModelProperty(value = "搜索内容")
    private String title;
}

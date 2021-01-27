package com.shoukailiang.community.article.req;

import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.BaseRequest;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分类查询条件属性
 */
@Data
@Accessors(chain = true)
public class CategoryREQ extends BaseRequest<Category> {
    /**
     * 分类名称
     */
    private String name;
    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer status;


}

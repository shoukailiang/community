package com.shoukailiang.community.article.vo;

import com.shoukailiang.community.entities.Label;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LabelVO extends Label {

    /**
     * 分类名称，不是表中的字段，不需要传，只有在search接口中会使用到一次
     */
    private String categoryName;
}

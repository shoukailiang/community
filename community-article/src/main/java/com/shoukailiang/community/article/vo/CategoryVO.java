package com.shoukailiang.community.article.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.entities.Label;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/17 21:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CategoryVO extends Category {

    /**
     * 分类下的标签集合
     */
    private List<Label> labelList;
}

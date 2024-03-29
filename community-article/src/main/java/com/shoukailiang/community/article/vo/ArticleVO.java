package com.shoukailiang.community.article.vo;

import com.baomidou.mybatisplus.annotation.*;
import com.shoukailiang.community.entities.Article;
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
 * @date 2021/5/17 17:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleVO extends Article {
    /**
     * 当前分类下的所有标签的接口
     */
    private List<Label> labelList;

    /**
     * 前端拿到ids就能渲染
     */
    private List<String> labelIds;

}


package com.shoukailiang.community.question.vo;

import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.entities.Question;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QuestionVO extends Question {
    /**
     * 当前分类下的所有标签的接口
     */
    private List<Label> labelList;

    /**
     * 前端拿到ids就能渲染
     */
    private List<String> labelIds;
}

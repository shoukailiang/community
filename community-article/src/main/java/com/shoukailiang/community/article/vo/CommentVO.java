package com.shoukailiang.community.article.vo;

import com.shoukailiang.community.entities.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 14:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentVO extends Comment {

    List<Comment> children;
}

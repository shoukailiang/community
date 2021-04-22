package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shoukailiang.community.article.mapper.ArticleMapper;
import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.entities.Comment;
import com.shoukailiang.community.article.mapper.CommentMapper;
import com.shoukailiang.community.article.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.feign.IFeignSystemController;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-07
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {


    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 通过文章id查询所有评论
     * @param articleId
     * @return
     */
    @Override
    public ResultVO findByArticleId(String articleId) {
        if(StringUtils.isBlank(articleId)){
            return ResultVOUtil.error("文章id不能为空");
        }
        Article article = articleMapper.findArticleAndLabelById(articleId);
        if(article==null){
            return ResultVOUtil.error("文章不存在");
        }
        List<Comment> comments = baseMapper.findByArticleId(articleId);

        return ResultVOUtil.success(comments);
    }

    /**
     * 通过评论id递归删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public ResultVO deleteById(String id) {
        if(StringUtils.isBlank(id)) {
            return ResultVOUtil.error("评论ID不能为空");
        }
        // 要删除的所有评论id
        ArrayList<String> ids = new ArrayList<>();
        //先把要删除的一级评论id放入到集合中
        ids.add(id);
        //递归的子评论 id 加入到集合中
        this.getIds(ids, id);
        //批量删除集合中的id
        baseMapper.deleteBatchIds(ids);
        return ResultVOUtil.success();
    }

    /**
     * 递归方法
     * @param ids 要删除的所有评论id
     * @param parentId
     */
    private void getIds(List<String> ids, String parentId){
        //查询子评论对象
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Comment> commentList = baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(commentList)) {
            for(Comment comment: commentList ) {
                String id = comment.getId();
                ids.add(id);
                // 递归继续查询子评论id
                this.getIds(ids, id);
            }
        }
    }
}

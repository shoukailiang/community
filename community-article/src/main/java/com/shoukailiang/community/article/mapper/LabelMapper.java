package com.shoukailiang.community.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
public interface LabelMapper extends BaseMapper<Label> {


    /**
     * 热门标签
     * @return
     */
    List<Label> selectHotTag();
}

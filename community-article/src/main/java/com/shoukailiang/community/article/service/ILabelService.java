package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

import java.util.List;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
public interface ILabelService extends IService<Label> {

    /**
     * 热门标签
     * @return
     */
    List<Label> findHotTag();

}

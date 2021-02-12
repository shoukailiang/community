package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

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
     * 条件与分页查询标签列表
     * @param req
     * @return
     */
    ResultVO queryPage(LabelREQ req);

}

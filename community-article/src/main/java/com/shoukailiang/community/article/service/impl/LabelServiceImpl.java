package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.article.mapper.LabelMapper;
import com.shoukailiang.community.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Override
    public Result queryPage(LabelREQ req) {
        IPage<Label> page = baseMapper.queryPage(req.getPage(), req);
        return Result.ok(page);
    }

    /**
     * 重写更新时间
     * @param label
     * @return
     */
    @Override
   public boolean updateById(Label label){
        label.setUpdateDate(new Date());
        return super.updateById(label);
    }
}

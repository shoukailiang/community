package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.article.mapper.LabelMapper;
import com.shoukailiang.community.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    public ResultVO queryPage(LabelREQ req) {
        IPage<Label> page = baseMapper.queryPage(req.getPage(), req);
        return ResultVOUtil.success(page);
    }


    @Override
    public List<Label> findHotTag() {
        List<Label> hotTagList =  baseMapper.selectHotTag();
        return hotTagList;
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

    @Resource
    private LabelMapper labelMapper;


    @Transactional
    @Override
    public void removeById(String id) {
        baseMapper.deleteById(id);
        labelMapper.deleteByLabelId(id);
    }
}

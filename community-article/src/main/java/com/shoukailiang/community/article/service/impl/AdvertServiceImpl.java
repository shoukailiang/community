package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shoukailiang.community.article.req.AdvertREQ;
import com.shoukailiang.community.entities.Advert;
import com.shoukailiang.community.article.mapper.AdvertMapper;
import com.shoukailiang.community.article.service.IAdvertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.aliyun.AliyunUtil;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.properties.ArticleProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements IAdvertService {
    @Autowired
    private ArticleProperties articleProperties;

    @Override
    public ResultVO queryPage(AdvertREQ req) {
        QueryWrapper<Advert> wrapper = new QueryWrapper();
        if(req.getStatus() != null) {
            wrapper.eq("status", req.getStatus());
        }
        if(StringUtils.isNotEmpty(req.getTitle())) {
            wrapper.like("title", req.getTitle());
        }
        wrapper.orderByDesc("status").orderByAsc("sort");
        // 分页对象
        return ResultVOUtil.success(baseMapper.selectPage(req.getPage(), wrapper));
    }


    @Transactional
    @Override
    public ResultVO deleteById(String id) {
        // 1. 先通过广告id查询图片url
        String imageUrl = baseMapper.selectById(id).getImageUrl();
        // 2. 先删除表中的广告信息
        baseMapper.deleteById(id);
        // 3. 删除oss上的图片
        if(StringUtils.isNotEmpty(imageUrl)) {
            AliyunUtil.delete(imageUrl, articleProperties.getAliyun());
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findByPosition(int position) {
        QueryWrapper<Advert> wrapper = new QueryWrapper<>();
        wrapper.eq("position", position);
        wrapper.eq("status", 1); // 正常
        wrapper.orderByAsc("sort"); 
        return ResultVOUtil.success(baseMapper.selectList(wrapper));
    }
}

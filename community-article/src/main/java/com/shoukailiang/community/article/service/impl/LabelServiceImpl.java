package com.shoukailiang.community.article.service.impl;

import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.article.mapper.LabelMapper;
import com.shoukailiang.community.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

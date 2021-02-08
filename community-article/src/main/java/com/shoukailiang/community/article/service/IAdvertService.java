package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.AdvertREQ;
import com.shoukailiang.community.entities.Advert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.Result;

/**
 * <p>
 * 广告信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
public interface IAdvertService extends IService<Advert> {
    /**
     * 分页查询广告列表
     * @param req
     * @return
     */
    Result queryPage(AdvertREQ req);


    /**
     * 删除广告即oss图片
     * @param id
     * @return
     */
    Result deleteById(String id);


    /**
     * 	查询指定广告位置下的所有广告信息接口，状态是正常的
     * @param position
     * @return
     */
    Result findByPosition(int position);
}

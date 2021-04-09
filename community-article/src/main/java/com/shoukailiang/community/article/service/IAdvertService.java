package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.AdvertREQ;
import com.shoukailiang.community.entities.Advert;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

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
     * 删除广告即oss图片
     * @param id
     * @return
     */
    ResultVO deleteById(String id);


    /**
     * 	查询指定广告位置下的所有广告信息接口，状态是正常的
     * @param position
     * @return
     */
    ResultVO findByPosition(int position);
}

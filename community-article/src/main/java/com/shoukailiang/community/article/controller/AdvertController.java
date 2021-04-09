package com.shoukailiang.community.article.controller;


import com.shoukailiang.community.article.req.AdvertREQ;
import com.shoukailiang.community.article.service.IAdvertService;
import com.shoukailiang.community.entities.Advert;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Api(value = "广告管理接口", description = "广告管理接口增删改查")
@RestController
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    private IAdvertService advertService;
}

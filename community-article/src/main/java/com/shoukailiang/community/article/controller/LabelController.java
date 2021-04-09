package com.shoukailiang.community.article.controller;


import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.article.service.ILabelService;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
@Api(value = "标签管理接口", description = "标签管理接口，提供标签的增、删、改、查")
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private ILabelService iLabelService;
}

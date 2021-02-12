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

    @ApiOperation("根据标签名称和状态查询标签的分页接口")
    @PostMapping("/search")
    public ResultVO search(@RequestBody LabelREQ req){
        return iLabelService.queryPage(req);
    }

    @ApiOperation("查询标签详情接口")
    @ApiImplicitParam(name="id", value="标签ID", required=true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id) {
        Label label = iLabelService.getById(id);
        return ResultVOUtil.success(label);
    }

    @ApiOperation("修改标签详情接口")
    @PutMapping
    public ResultVO view(@RequestBody Label label) {
        // 重写updateTime
        iLabelService.updateById(label);
        return ResultVOUtil.success();
    }

    @ApiOperation("增加标签详情接口")
    @PostMapping
    public ResultVO save(@RequestBody Label label) {
        iLabelService.save(label);
        return ResultVOUtil.success();
    }

    @ApiImplicitParam(name = "id",value = "标签id",required = true)
    @ApiOperation("删除标签详情接口")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        iLabelService.removeById(id);
        return ResultVOUtil.success();
    }
}

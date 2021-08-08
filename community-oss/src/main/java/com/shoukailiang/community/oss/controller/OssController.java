package com.shoukailiang.community.oss.controller;

import com.shoukailiang.community.feign.IFeignOssController;
import com.shoukailiang.community.oss.service.OssService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.enums.PlatformEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 */
@Api(value = "文件管理接口", description = "文件管理接口，上传或删除图片文件")
@RequestMapping("/file")
@RestController
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传文件到OSS服务器")
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        ResultVO result = ossService.uploadFileToOss(PlatformEnum.ARTICEL,file);
        return result;
    }

    @ApiImplicitParam(name = "fileUrl", value = "要删除的文件URL", required = true)
    @ApiOperation("根据文件URL删除在OSS服务器中对应文件")
    @DeleteMapping("/delete")
    public ResultVO delete(@RequestParam(value = "fileUrl", required = true)
                                 String fileUrl) {
        ResultVO delete = ossService.delete(fileUrl);
        return delete;
    }

}

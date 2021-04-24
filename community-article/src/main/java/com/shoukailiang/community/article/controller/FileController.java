package com.shoukailiang.community.article.controller;

import com.shoukailiang.community.util.aliyun.AliyunUtil;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.PlatformEnum;
import com.shoukailiang.community.util.properties.AliyunProperties;
import com.shoukailiang.community.util.properties.ArticleProperties;
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
public class FileController {

    @Autowired
    private ArticleProperties articleProperties;

    @ApiOperation("上传文件到OSS服务器")
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {
        // 获取阿里云 OSS 相关配置信息
        AliyunProperties aliyun = articleProperties.getAliyun();
        ResultVO result = AliyunUtil.uploadFileToOss(PlatformEnum.ARTICEL, file, aliyun);
        return result;
    }

    @ApiImplicitParam(name = "fileUrl", value = "要删除的文件URL", required = true)
    @ApiOperation("根据文件URL删除在OSS服务器中对应文件")
    @DeleteMapping("/delete")
    public ResultVO delete(@RequestParam(value = "fileUrl", required = true) String fileUrl) {
        ResultVO delete = AliyunUtil.delete(fileUrl, articleProperties.getAliyun());
        return delete;
    }
}

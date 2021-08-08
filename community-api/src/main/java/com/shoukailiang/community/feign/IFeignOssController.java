package com.shoukailiang.community.feign;

import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.enums.PlatformEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/8/7 21:35
 */
@FeignClient(value = "oss-server",path = "/oss")
public interface IFeignOssController {

    @ApiOperation("Feign接口-上传图片")
    @PostMapping("/api/feign/oss/uploadImage")
    ResultVO uploadFileToOss(@RequestParam("platformEnum") PlatformEnum platformEnum,@RequestParam("file") MultipartFile file);

    @ApiOperation("Feign接口-删除图片")
    @DeleteMapping("/api/feign/oss/deleteImage")
    ResultVO deleteImage(@PathVariable("imageUrl") String imageUrl);
}

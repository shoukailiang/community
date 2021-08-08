package com.shoukailiang.community.oss.feign;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.shoukailiang.community.feign.IFeignOssController;
import com.shoukailiang.community.oss.properties.AliyunProperties;
import com.shoukailiang.community.oss.service.OssService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.PlatformEnum;
import io.swagger.annotations.Api;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/8/7 20:46
 */
@Api(value = "被远程调用的oss的微服务接口",description = "被远程调用的oss的微服务接口")
@RestController
public class FeignOssController implements IFeignOssController {

    @Autowired
    private OssService ossService;


    @Override
    public ResultVO uploadFileToOss(@RequestParam("platformEnum") PlatformEnum platformEnum ,@RequestParam("file") MultipartFile file) {
        return ossService.uploadFileToOss(platformEnum,file);
    }

    @Override
    public ResultVO deleteImage(String imageUrl) {
        return ossService.delete(imageUrl);
    }
}

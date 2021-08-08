package com.shoukailiang.community.oss.service;

import com.shoukailiang.community.oss.properties.AliyunProperties;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.enums.PlatformEnum;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    ResultVO uploadFileToOss(PlatformEnum platformEnum, MultipartFile file);

    ResultVO delete(String fileUrl);
}

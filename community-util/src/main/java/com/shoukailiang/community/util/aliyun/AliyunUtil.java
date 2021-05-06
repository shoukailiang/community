package com.shoukailiang.community.util.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.PlatformEnum;
import com.shoukailiang.community.util.properties.AliyunProperties;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * 阿里云工具类
 */
public final class AliyunUtil {

    /**
     * 上传图片文件
     * @param platformEnum 类型：文章，用户
     * @param file  MultipartFile文件对象
     * @param aliyun AliyunProperties 阿里云配置
     * @return
     */
    public static ResultVO uploadFileToOss(PlatformEnum platformEnum, MultipartFile file, AliyunProperties aliyun ) {
        // 上传
        // 上传文件所在目录名，当天上传的文件放到当天日期的目录下。
        String folderName = platformEnum.name().toLowerCase() + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd");
        // 保存到 OSS 中的文件名，采用 UUID 命名。
        String fileName = UUID.randomUUID().toString().replace("-", "");
        // 从原始文件名中，获取文件扩展名
        String fileExtensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 文件在 OSS 中存储的完整路径
        String filePath = folderName + "/" + fileName + fileExtensionName;
        OSS ossClient = null;
        try {
            // 获取 OSS 客户端实例
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
            // 上传文件到OSS 并响应结果
            PutObjectResult putObjectResult = ossClient.putObject(aliyun.getBucketName(), filePath, file.getInputStream());
            ResponseMessage response = putObjectResult.getResponse();
            if(response == null) {
                // 上传成功
                // 返回上传文件的访问完整路径
                return ResultVOUtil.success( aliyun.getBucketDomain() + filePath );
            }else {
                // 上传失败，OOS服务端会响应状态码和错误信息
                String errorMsg = "响应的错误状态码是【" + response.getStatusCode() +"】，" +
                        "错误信息【"+response.getErrorResponseAsString()+"】";
                return ResultVOUtil.error(errorMsg);
            }
        } catch (Exception e) {
            return ResultVOUtil.error(e.getMessage());
        } finally {
            if (ossClient != null) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    /**
     * 根据文件url删除
     * @param fileUrl
     */
    public static ResultVO delete(String fileUrl, AliyunProperties aliyun) {
        // 去除文件 url 中的 Bucket域名
        String filePath = fileUrl.replace(aliyun.getBucketDomain(), "");

        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
            // 删除
            ossClient.deleteObject(aliyun.getBucketName(), filePath);
            return ResultVOUtil.success();
        } catch (Exception e) {
            return ResultVOUtil.error("删除失败："+e.getMessage());
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
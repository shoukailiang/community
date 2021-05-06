package com.shoukailiang.community.feign;

import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// value 指定是哪个微服务接口，
// path 与接口实现类的微服务中配置的 context-path 值一致
@FeignClient(value="article-server", path = "/article")
public interface IFeignArticleController {

    // List<String> labelIds
    // allowMultiple=true 表示数组格式的参数,dataType="String" 表示数组中参数的类型
    @ApiImplicitParam(allowMultiple = true, dataType = "String", name = "ids",
            value = "标签ID集合", required = true)
    @ApiOperation("Feign接口-根据标签ids查询对应的标签信息")
    @GetMapping("/api/feign/label/list/{ids}")
    List<Label> getLabelListByIds(@PathVariable("ids") List<String> labelIds);


    @ApiOperation("Feign接口-更新文章表和评论表中的用户信息")
    @PutMapping("/feign/article/user")
    boolean updateUserInfo(@RequestBody UserInfoREQ req);
}

package com.shoukailiang.community.article.feign;

import com.shoukailiang.community.article.service.ILabelService;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.feign.IFeignArticleController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "被远程调用的文章的微服务接口",description = "被远程调用的文章的微服务接口")
@RestController
public class FeignArticleController implements IFeignArticleController {
    @Autowired
    private ILabelService labelService;

    @Override
    public List<Label> getLabelListByIds(List<String> labelIds) {
        List<Label> labels = labelService.listByIds(labelIds);
        return labels;
    }
}

package com.shoukailiang.community.article.req;

import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "LabelREQ对象",description = "标签查询条件")
public class LabelREQ extends BaseRequest<Label> {

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "分类id")
    private String categoryId;
}

package com.shoukailiang.community.question.req;

import com.shoukailiang.community.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "QuestionByLabelIdREQ对象",description = "通过labelId获取问题")
public class QuestionByLabelIdREQ extends BaseRequest {
    private String LabelId;
}

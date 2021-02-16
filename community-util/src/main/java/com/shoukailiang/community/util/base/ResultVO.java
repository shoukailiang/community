package com.shoukailiang.community.util.base;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 6775184589222575187L;
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String message;

    /**
     * 具体内容
     **/
    private T Data;

    public String toJsonString() {
        return JSON.toJSONString(this);
    }
}

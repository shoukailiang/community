package com.shoukailiang.community.util.base;

import lombok.Data;

import java.io.Serializable;

@Data
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
}

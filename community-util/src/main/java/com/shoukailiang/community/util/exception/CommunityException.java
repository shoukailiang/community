package com.shoukailiang.community.util.exception;

import com.shoukailiang.community.util.enums.ResultEnum;
import lombok.Getter;

@Getter
public class CommunityException extends RuntimeException {
    private Integer code;

    public CommunityException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public CommunityException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

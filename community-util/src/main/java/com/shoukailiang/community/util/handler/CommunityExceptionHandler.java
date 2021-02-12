package com.shoukailiang.community.util.handler;

import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.exception.CommunityException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommunityExceptionHandler {
    @ExceptionHandler(value = CommunityException.class)
    @ResponseBody
    public ResultVO handlerSellerException(CommunityException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}

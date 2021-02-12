package com.shoukailiang.community.util.base;

import com.alibaba.fastjson.JSON;
import com.shoukailiang.community.util.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 用于封装接口统一响应结果
 */
public final class ResultVOUtil implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(ResultVOUtil.class);

    private static final long serialVersionUID = 1L;

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(data);
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
        return resultVO;
    }


    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(msg);
        logger.debug("返回错误：code={}, message={}", code, msg);
        return resultVO;
    }

    public static ResultVO error(String msg) {
        logger.debug("返回错误：code={}, message={}", ResultEnum.ERROR.getCode(), msg);
        return error(ResultEnum.ERROR.getCode(), msg);
    }

}
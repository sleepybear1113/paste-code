package com.xjx.pastecode.exception;

import com.xjx.pastecode.utils.returns.ResultCodeConstant;

import java.io.Serial;
import java.io.Serializable;

/**
 * 前端异常
 *
 * @author XJX
 * @date 2021/9/3 9:40
 */
public class FrontException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 2332850776216529844L;

    private Integer code;

    public FrontException(String message) {
        super(message);
        code = ResultCodeConstant.CodeEnum.COMMON_ERROR.getCode();
    }

    public FrontException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

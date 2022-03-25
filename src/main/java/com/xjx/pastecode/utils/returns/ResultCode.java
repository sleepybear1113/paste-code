package com.xjx.pastecode.utils.returns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author XieJiaxing
 * @date 2021/8/10 0:33
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResultCode implements Serializable {
    @Serial
    private static final long serialVersionUID = -2938642554402365880L;

    /**
     * {@link ResultCodeConstant.CodeEnum}
     */
    private Integer code;
    private String message;
    private Object result;

    public ResultCode(Object result) {
        if (result instanceof ResultCode r) {
            this.code = r.getCode();
            this.message = r.getMessage();
            this.result = r.getResult();
        } else {
            this.code = ResultCodeConstant.CodeEnum.SUCCESS.getCode();
            this.message = null;
            this.result = result;
        }
    }

    public ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.result = null;
    }

    public ResultCode(String message) {
        this.code = ResultCodeConstant.CodeEnum.COMMON_ERROR.getCode();
        this.message = message;
        this.result = null;
    }
}

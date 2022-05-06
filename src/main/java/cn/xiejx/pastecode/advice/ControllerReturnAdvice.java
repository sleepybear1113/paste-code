package cn.xiejx.pastecode.advice;

import cn.xiejx.pastecode.exception.FrontException;
import cn.xiejx.pastecode.utils.returns.ResultCodeConstant;
import cn.xiejx.pastecode.utils.returns.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 将信息、异常封装格式给前端接口
 *
 * @author xjx
 * @date 2021/10/7 14:44
 */
@RestControllerAdvice
@Slf4j
public class ControllerReturnAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public ResultCode beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return new ResultCode(o);
    }

    @ExceptionHandler(value = FrontException.class)
    public ResultCode handlerGlobeMyException(HttpServletRequest request, FrontException exception) {
        log.warn("用户错误：" + exception.getMessage());
        return new ResultCode(ResultCodeConstant.CodeEnum.COMMON_ERROR.getCode(), exception.getMessage(), null);
    }

    @ExceptionHandler(value = Exception.class)
    public ResultCode handlerGlobeException(HttpServletRequest request, Exception exception) {
        log.error("系统错误：" + exception.getMessage(), exception);
        return new ResultCode(ResultCodeConstant.CodeEnum.SYSTEM_ERROR.getCode(), exception.getMessage(), null);
    }

}
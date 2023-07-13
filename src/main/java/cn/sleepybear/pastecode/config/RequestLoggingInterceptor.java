package cn.sleepybear.pastecode.config;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * There is description
 *
 * @author sleepybear
 * @date 2023/07/13 15:37
 */
@Slf4j
public class RequestLoggingInterceptor implements HandlerInterceptor {
    public static final String[] IGNORE_SUFFIX_LIST = {".js", ".css"};

    @Override
    public boolean preHandle(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        // 在请求处理之前记录请求信息
        String url = request.getRequestURL().toString();
        for (String s : IGNORE_SUFFIX_LIST) {
            if (url.endsWith(s)) {
                return true;
            }
        }
        String method = request.getMethod();
        log.debug("Request - [{}] {}", StringUtils.leftPad(method, 4), url);
        return true;
    }

    @Override
    public void postHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, ModelAndView modelAndView) {
        // 请求处理完成后的操作（可选）
    }

    @Override
    public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
        // 请求完成后处理的操作（可选）
    }
}
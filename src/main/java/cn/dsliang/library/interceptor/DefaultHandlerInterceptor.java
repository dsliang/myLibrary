package cn.dsliang.library.interceptor;

import cn.dsliang.library.common.ApiResponse;
import cn.dsliang.library.enums.ApiResponseEnum;
import cn.dsliang.library.helper.SecurityHelper;
import cn.dsliang.library.helper.WebHelper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultHandlerInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object arg2, ModelAndView arg3)
            throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        WebHelper.setRequest(req);
        WebHelper.setResponse(res);

        String uri = req.getServletPath();

        if (!uri.equals("/api/common/login") && !SecurityHelper.isLogin()) {
            WebHelper.writeJson(ApiResponse.error(ApiResponseEnum.UNAUTHORIZED.getCode(), ApiResponseEnum.UNAUTHORIZED.getMessage()));
            return false;
        }

        return true;
    }
}

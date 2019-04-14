package cn.dsliang.library.helper;

import cn.dsliang.library.util.JacksonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class WebHelper {
    private final static ThreadLocal<HttpServletRequest> threadLocalRequest = new ThreadLocal<HttpServletRequest>();
    private final static ThreadLocal<HttpServletResponse> threadLocalResponse = new ThreadLocal<HttpServletResponse>();

    public static void setRequest(HttpServletRequest request) {
        threadLocalRequest.set(request);
    }

    public static HttpServletRequest getRequest() {
        return threadLocalRequest.get();
    }

    public static void setResponse(HttpServletResponse response) {
        threadLocalResponse.set(response);
    }

    public static HttpServletResponse getResponse() {
        return threadLocalResponse.get();
    }

    public static void writeJson(Object obj) {
        HttpServletResponse res = getResponse();
        res.setContentType("application/json;charset=utf-8");
        try {
            res.getWriter().write(JacksonUtil.toJson(obj));
            res.getWriter().flush();
            res.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
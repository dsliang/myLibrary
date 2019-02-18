package cn.dsliang.library.helper;

import cn.dsliang.library.entity.User;
import cn.dsliang.library.service.UserService;
import cn.dsliang.library.util.CommUtil;
import cn.dsliang.library.util.CookieUtil;
import cn.dsliang.library.util.JacksonUtil;
import cn.dsliang.library.util.StringUtil;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.CollationKey;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class SecurityHelper {

    private static final String TOKEN = "token";

    private final static Cache<String, Object> session = CacheBuilder.newBuilder()
            // 设置cache的初始大小为10，要合理设置该值
            .initialCapacity(100)
            // 设置并发数为10，即同一时间最多只能有10个线程往cache执行写入操作
            .concurrencyLevel(10)
            // 设置cache中的数据在写入之后的存活时间为7天
            .expireAfterWrite(7, TimeUnit.DAYS)
            // 构建cache实例
            .build();

    private static void setSession(String key, Object value) {
        session.put(key, value);
    }


    private static void removeSession(String key) {
        session.invalidate(key);
    }

    public static void setUser(User user) {
        String token = CommUtil.getUUID();

        setUser(token, user);
    }

    public static void setUser(String token, User user) {

        HttpServletResponse response = WebHelper.getResponse();
        CookieUtil.addCookie(response, TOKEN, token, (int) TimeUnit.DAYS.toSeconds(7));

        setSession(token, user);
    }

    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        if (StringUtil.isEmpty(getToken()))
            return false;

        if (getUser() == null) {
            CookieUtil.removeCookie(WebHelper.getResponse(), TOKEN);
            return false;
        }

        return true;
    }

    public static String getToken() {
        HttpServletRequest request = WebHelper.getRequest();
        String token = CookieUtil.getCookieValue(request, TOKEN);
        return token;
    }

    /**
     * 获取操作员
     *
     * @return
     */
    public static User getUser() {
        User user = (User) session.getIfPresent(getToken());
        return user;
    }

    public static void removeUser() {
        HttpServletRequest request = WebHelper.getRequest();
        HttpServletResponse response = WebHelper.getResponse();

        String token = CookieUtil.getCookieValue(request, TOKEN);

        removeSession(token);
        CookieUtil.removeCookie(response, TOKEN);
    }
}

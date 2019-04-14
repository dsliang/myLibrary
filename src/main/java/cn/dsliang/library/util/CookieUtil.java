package cn.dsliang.library.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    static public void addCookie(HttpServletResponse response, String name, String value, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }

    static public void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name,null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    static public Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    static public String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null)
            return cookie.getValue();

        return null;
    }
}

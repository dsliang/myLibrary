package cn.dsliang.library.utils;

public class StringUtil {
    static public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    static public boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}

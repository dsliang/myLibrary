package cn.dsliang.library.util;

public class StringUtil {
    static public boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    static public boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
}

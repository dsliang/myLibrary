package cn.dsliang.library.util;

import java.util.UUID;

public class CommUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

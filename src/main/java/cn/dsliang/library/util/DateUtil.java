package cn.dsliang.library.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String DATE_FORMAT = "yyyy-MM-dd";


    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date 输入日期
     * @param day  要增加或减少的天数
     */
    public static Date addDay(Date date, int day) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, day);

        return cd.getTime();
    }

    /**
     * 比较时间大小（第一个时间在第二个时间之前返回-1；第一个时间在第二个时间之后返回1，相同返回0）
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() < date2.getTime())
            return -1;
        else if (date1.getTime() > date2.getTime())
            return 1;
        else
            return 0;
    }
}

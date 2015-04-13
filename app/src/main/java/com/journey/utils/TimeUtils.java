package com.journey.utils;

import android.content.Context;

import com.journey.data.Const;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeUtils {

    public static String unix2str(String unix) {
//        Long timestamp = Long.parseLong(unix) * 1000;
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-HH/mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//        String date = sdf.format(new java.util.Date(timestamp));
        return getTimekeyEnd6(unix);
    }

    // 获得提交服务器的同步time
    public static String getUnixTime(final Context context) {
        return String.valueOf((int) (System.currentTimeMillis() / 1000 + context.getSharedPreferences(Const.SP_CONFIG,
                Context.MODE_PRIVATE).getInt(Const.SP_UNIX_TIME_DIF, 0)));
    }

    /**
     * @param unix_time
     * @return
     */
    public static String getTimeKey(String unix_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        long lcc_time = Long.valueOf(unix_time);
        re_StrTime = sdf.format(new Date(lcc_time * 1000L));
        StringBuilder resBuilder = new StringBuilder(re_StrTime);
        resBuilder.setCharAt(5, '0');
        return resBuilder.toString();

    }

    /**
     * time 后六位
     *
     * @param unix_time
     * @return
     */
    public static String getTimekeyEnd6(String unix_time) {
        int length = unix_time.length();
        return unix_time.substring(length - 6, length);
    }

    public static String time2Date(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat(formats);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String date = sdf.format(new java.util.Date(timestamp));
        return date;
    }
}

package com.journey.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

/**
 * 说明：对日期格式的格式化与转换操作等一系列操作
 */
public class DateUtils {

    public String timeStamp2DateStr(String timestampString, String formats) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat(formats);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String date = sdf.format(new java.util.Date(timestamp));
        return date;
    }

    public Date timeStamp2Date(String timestampString) {
        Long timestamp = Long.parseLong(timestampString) * 1000;
        return new java.util.Date(timestamp);
    }

    public Date dateStr2Date(String dateStr,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = null;
        try {
            date = sdf.parse(dateStr);//将字符串s通过转换器转换为date类型
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    public String date2DateStr(Date date,String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(date);
    }

    /**
     * 获取开始时间的时间戳
     * */
    public long getStartTime(String unixtime){ //String unixtime

        String dateStr = timeStamp2DateStr(unixtime, "yyyy-MM-dd HH:mm:ss");
        // MyApplication.htlog.i("m2005 dateStr--> "+dateStr);
        Date date = dateStr2Date(dateStr, "yyyy-MM-dd HH:mm");
        // MyApplication.htlog.i("m2005 date--> "+date.getTime());
        int min = date.getMinutes();
        int temp_diff;
        if(min<10){
            //  temp_min = 15;
            temp_diff = 15 - min;
        }else if(min<25){
            temp_diff = 30 - min;
        }else if(min<40){
            temp_diff = 45 - min;
        }else if(min<55){
            temp_diff = 60 - min;
        }else{
            temp_diff = 75 - min;
        }

        long startTime = date.getTime()/1000+ temp_diff*60;
        //  MyApplication.htlog.i("m2005 startTime->"+startTime);
        //  dateStr = timeStamp2DateStr(String.valueOf(startTime),"yyyy-MM-dd HH:mm:ss");
        //  MyApplication.htlog.i("m2005 startTime str->"+dateStr);

        /*Date d1 = dateStr2Date("2014-10-22 09:26:23", "yyyy-MM-dd HH:mm:ss");
        String s1 = "13011111111"+"123456"+d1.getTime()/1000;
        MyApplication.htlog.i("token1->"+MD5tools.md5(s1)+" is "+MD5tools.md5(s1).equals("7c700313d760a7ae20193ff6aa629a49"));

        Date d2 = dateStr2Date("2014-10-27 15:40:38", "yyyy-MM-dd HH:mm:ss");
        String s2 = "15812345678"+"123456"+d2.getTime()/1000;
        MyApplication.htlog.i("token2->"+MD5tools.md5(s2));*/

        return startTime;
    }

    /**
     * 获取结束时间的时间戳
     * */
    public long getEndTime(String unixtime){ //String unixtime
        String dateStr = timeStamp2DateStr(unixtime, "yyyy-MM-dd HH:mm:ss");
        Date date = dateStr2Date(dateStr, "yyyy-MM-dd HH");
        //   MyApplication.htlog.i("m2005 endTime date--> "+date.getTime());

        long endTime = date.getTime()/1000+60*60*2;
        //    MyApplication.htlog.i("m2005 endTime ->"+endTime);

        //     dateStr = timeStamp2DateStr(String.valueOf(endTime),"yyyy-MM-dd HH:mm:ss");
        //      MyApplication.htlog.i("m2005 endTime Str--> "+dateStr);
        return endTime;
    }

    public static Date strToDate(String strFormat, String dateValue) {
        if (dateValue == null) {
            return null;
        }
        if (strFormat == null) {
            strFormat = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        Date newDate = null;
        try {
            newDate = dateFormat.parse(dateValue);
        } catch (ParseException pe) {
            newDate = null;
        }
        return newDate;
    }

    public static boolean lagLessThanZero(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        if (str2 == null) {
            two = new Date();
        } else {
            try {
                two = df.parse(str2);
            } catch (ParseException pe) {
                two = new Date();
            }
        }
        try {
            one = df.parse(str1);
        } catch (ParseException pe) {
            one = new Date();
        }
        long oneL = one.getTime();
        long twoL = two.getTime();
        return (oneL - twoL) <= 0;

    }

    public static String dateToStr(String strFormat, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
        return dateFormat.format(date);
    }

    public static int countDays(Date begin, Date end) {
        int days = 0;
        Calendar c_b = Calendar.getInstance();
        Calendar c_e = Calendar.getInstance();
        c_b.setTime(begin);
        c_e.setTime(end);
        while (c_b.before(c_e)) {
            days++;
            c_b.add(Calendar.DAY_OF_YEAR, 1);
        }
        return days;
    }

    public static String getNearTime(String str1) {
        String result = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = new Date();
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            System.out.println(day + "天" + hour + "小时" + min + "分钟" + sec + "秒");
            long m = day * 24 * 60 + hour * 60 + min;

            if (m <= 1) {
                result = "一分钟前";
            } else if (m > 1 && m <= 5) {
                result = "五分钟前";
            } else if (m > 5 && m <= 30) {
                result = "半小时前";
            } else if (m > 30 && m <= 60) {
                result = "一小时前";
            } else if (m > 60 && m <= 60 * 2) {
                result = "两小时前";
            } else if (m > 60 * 2 && m <= 60 * 24) {
                result = "一天前";
            } else if (m > 60 * 24 && m <= 60 * 24 * 2) {
                result = "两天前";
            } else if (m > 60 * 24 * 2 && m <= 60 * 24 * 7) {
                result = "一星期前";
            } else if (m > 60 * 24 * 7 && m <= 60 * 24 * 30) {
                result = "一个月前";
            } else if (m > 60 * 24 * 30 && m <= 60 * 24 * 30 * 6) {
                result = "六个月前";
            } else if (m > 60 * 24 * 30 * 6) {
                result = "很久以前";
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
        }
        return result;
    }

    public static long getDistanceDays(String str1, String str2) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days = 0;
        try {

            if (str1 == null) {
                one = new Date();
            } else {
                one = df.parse(str1);
            }

            if (str2 == null) {
                two = new Date();
            } else {
                two = df.parse(str2);
            }

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {

            if (str1 == null) {
                one = new Date();
            } else {
                one = df.parse(str1);
            }

            if (str2 == null) {
                two = new Date();
            } else {
                two = df.parse(str2);
            }

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new long[]{day, hour, min, sec};
    }

    public static String getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        try {

            if (str1 == null) {
                one = new Date();
            } else {
                one = df.parse(str1);
            }

            if (str2 == null) {
                two = new Date();
            } else {
                two = df.parse(str2);
            }

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }

    public static String getDistanceTimeMinute(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        try {

            if (str1 == null) {
                one = new Date();
            } else {
                one = df.parse(str1);
            }

            if (str2 == null) {
                two = new Date();
            } else {
                two = df.parse(str2);
            }

            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            // sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min *
            // 60);
            if (day >= 1) {
                return day + "天";
            } else if (hour >= 1) {
                return hour + "小时";
            } else {
                return min + "分";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分";
    }

    public static String getConversationTime(String str1) {
        String result = "";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        try {
            one = df.parse(str1);
            two = new Date();

            if (one.getYear() == two.getYear() && one.getMonth() == two.getMonth() && one.getDay() == two.getDay()) {
                result = "今天  " + dateToStr("HH:mm", one);
                ;
            } else {
                result = dateToStr("yyyy-MM-dd", one);
            }
        } catch (ParseException e) {
        }
        return result;
    }

    public static String millSecondToDateExt(long millSecond, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(millSecond));
    }

    public static int isNextMonth(Date clickDate, int currentYear, int currentMonth) {
        int clickYear = clickDate.getYear() + 1900;
        int clickMonth = clickDate.getMonth();
        if (clickYear > currentYear) {
            return 1;
        } else if (clickYear == currentYear) {
            if (clickMonth > currentMonth) {
                return 1;
            } else if (clickMonth < currentMonth) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    @SuppressWarnings ("deprecation")
    public static boolean isAfterStartTime(String endTime, String startTime) {
        Date startDate = strToDate("yyyy-MM-dd", startTime);
        Date endDate = strToDate("yyyy-MM-dd", endTime);
        return endDate.getYear() >= startDate.getYear() && (endDate.getYear() != startDate.getYear() || endDate.getMonth() >= startDate.getMonth() && (endDate.getMonth() != startDate.getMonth() || endDate.getDate() >= startDate.getDate()));
    }

    public static boolean isMoreThanThirtyDays(String startTime, String endTime) {
        Date startDate = strToDate("yyyy-MM-dd", startTime);
        Date endDate = strToDate("yyyy-MM-dd", endTime);
        long start = startDate.getTime();
        long end = endDate.getTime();
        int day = (int) ((start - end) / (24 * 60 * 60 * 1000));
        return day + 1 > 30;
    }

    public static boolean isContainBusyTime(String startTime, String endTime, Map<String, Integer> timeMap) {
        boolean temp = false;
        Date startDate = strToDate("yyyy-MM-dd", startTime);
        Date endDate = strToDate("yyyy-MM-dd", endTime);
        long start = startDate.getTime();
        long end = endDate.getTime();
        int day = (int) ((end - start) / (24 * 60 * 60 * 1000));
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < day; i++) {
            cal.setTime(startDate);
            cal.add(Calendar.DATE, i);
            Date date = cal.getTime();
            String dateStr = dateToStr("yyyy-MM-dd", date);
            if (timeMap.containsKey(dateStr)) {
                temp = true;
                break;
            }
        }
        return temp;
    }

    public static String date2Zodica(Date birthday) {
        final String[] zodiacArr = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        return zodiacArr[cal.get(Calendar.YEAR) % 12];
    }

    public static String date2Constellation(Date birthday) {
        final String[] constellationArr = {"水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
        final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }
}


package com.journey.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.journey.data.Const;

/**
 * Created by Administrator on 2015/3/16.
 */
public class PrefsSettingUtils {
    public static boolean getNotifyswitch(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("notifyswitch", false);
    }

    public static void setNotifyswitch(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("notifyswitch", flag).commit();
    }

    ////


    public static boolean getLive_SelfFresh(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("live_selffresh", true);
    }

    public static void setLive_SelfFresh(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("live_selffresh", flag).commit();
    }

    ////

    public static boolean getLive_StatebarFresh(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("live_statebarfresh", true);
    }

    public static void setLive_StatebarFresh(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("live_statebarfresh", flag).commit();
    }

    ////

    public static boolean getLive_StateHigh(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("live_statehigh", true);
    }

    public static void setLive_StateHigh(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("live_statehigh", flag).commit();
    }

    ////

    public static boolean getLive_StateMid(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("live_statemid", true);
    }

    public static void setLive_StateMid(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("live_statemid", flag).commit();
    }

    ////

    public static boolean getLive_StateLow(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("live_statelow", true);
    }

    public static void setLive_StateLow(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("live_statelow", flag).commit();
    }

    ////

    public static boolean isFreshNewsFAOpen(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("freshnewsfaopen", false);
    }

    public static void setFreshNewsFA(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("freshnewsfaopen", flag).commit();
    }


    ///

    public static String getLive_NotifySounds(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getString("live_notifysounds", "");
    }

    public static void setLive_NotifySounds(final Context context, String url) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putString("live_notifysounds", url).commit();
    }

    ///

    public static String getLive_NotifySoundsName(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getString("live_notifysoundsname", "");
    }

    public static void setLive_NotifySoundsName(final Context context, String url) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putString("live_notifysoundsname", url).commit();
    }

    ////

    public static boolean isFreshNewsFASoundsOpen(final Context context) {
        return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getBoolean("FreshNewsFASoundsOpen", true);
    }

    public static void setFreshNewsFASoundsOpen(final Context context, boolean flag) {
        context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).edit().putBoolean("FreshNewsFASoundsOpen", flag).commit();
    }

    //// 是否已经登录我

    public static boolean islogin(final Context context) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE);
//        String p1 = sharedPreferences.getString(MXXXXConstant.HT_USER_P1, "");
//        String p2 = sharedPreferences.getString(MXXXXConstant.HT_USER_P2, "");
//
//        if (!"".equals(p1) && !"".equals(p2)) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }

    // 获得用户名
    public static String getUserId(final Context context) {
       // return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getString(MXXXXConstant.USER_JSON_ID, "");
        return null;
    }

    // 获得用户token
    public static String getUserToken(final Context context) {
       // return context.getSharedPreferences(Const.SP_SETTING, Context.MODE_PRIVATE).getString(MXXXXConstant.USER_JSON_TOKEN, "");
        return null;
    }
}

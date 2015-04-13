package com.journey.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

public class ManifestData {

    private static String versionName;
    private static int versionCode;
    private static String packagename;

    private static Object readKey(Context context, String keyName) {
        try {
            ApplicationInfo appi = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            PackageManager pm = context.getPackageManager();
            PackageInfo pinfo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);
            Bundle bundle = appi.metaData;
            Object value = bundle.get(keyName);
            versionCode = pinfo.versionCode;
            versionName = pinfo.versionName;
            packagename = context.getPackageName();
            return value;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    public static int getInt(Context context, String keyName) {
        return (Integer) readKey(context, keyName);
    }

    public static String getString(Context context, String keyName) {
        return (String) readKey(context, keyName);
    }

    public static Boolean getBoolean(Context context, String keyName) {
        return (Boolean) readKey(context, keyName);
    }

    public static Object get(Context context, String keyName) {
        return readKey(context, keyName);
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getPackageName() {
        return packagename;
    }
}

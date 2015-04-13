package com.journey.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.journey.R.string;

import com.journey.R;

import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/3/17.
 */
public class AssetsUtil {
    @SuppressLint ("NewApi")
    public static void startAnotherApp(Context context, String packageName, String targetActivity) {
        if (checkPackageNameExists(context, packageName)) {
            if (TextUtils.isEmpty(targetActivity)) {
                PackageManager packageManager = context.getPackageManager();
                Intent intent = new Intent();
                intent = packageManager.getLaunchIntentForPackage(packageName);
                context.startActivity(intent);
            } else {
                Intent intent = new Intent();
                ComponentName cn = new ComponentName(packageName, targetActivity);
                intent.setComponent(cn);
                ((Activity) context).startActivityForResult(intent, 1000);
            }
        } else {// 未安装，跳转至market下载该程序
            Uri uri = Uri.parse("market://details?id=" + packageName);// id为包名
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        }
    }

    public static boolean checkPackageNameExists(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static boolean checkClassNameExists(String className) {
        if (className == null || "".equals(className))
            return false;
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Bitmap getBitmapFromAssetsFileName(Context context, String fileName) {
        try {
            return getBitmapFromStream(getStreamFromAssetsFileName(context, fileName), 1);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static Bitmap getBitmapFromResFileName(Context context, String resName) {
        try {
            return getBitmapFromResFileID(context, getResIDFromResName(context, "drawable", resName));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static String getStringFromResName1(Context context, String resName) {
        try {
            return context.getResources().getString(getResIDFromResName(context, "string", resName));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

    public static String getStringFromResName2(Context context, String resName) {
        Class<string> c = R.string.class;
        Field field;
        int value = 0;
        try {
            field = c.getDeclaredField(resName);
            value = field.getInt(null);
            return context.getResources().getString(value);
        } catch (SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    public static Bitmap getBitmapFromResFileID(Context context, int resId) {
        try {
            return getBitmapFromStream(getStreamFromResID(context, resId), 1);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static Drawable getDrawableFromAssetsFileName(Context context, String fileName) {
        try {
            BitmapDrawable drawable = null;
            InputStream stream = getStreamFromAssetsFileName(context, fileName);
            drawable = new BitmapDrawable(stream);
            stream.close();
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static Drawable getDrawableFromResFileID(Context context, int resId) {
        try {
            BitmapDrawable drawable = null;
            InputStream stream = getStreamFromResID(context, resId);
            drawable = new BitmapDrawable(stream);
            stream.close();
            return drawable;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static Drawable getDrawableFromResFileName(Context context, String fileName) {
        try {
            return getDrawableFromResFileID(context, getResIDFromResName(context, "drawable", fileName));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public static int getResIDFromResName(Context context, String resFolder, String fileName) {
        int resID = -1;
        try {
            fileName = fileName.replace(".png", "");
            fileName = fileName.replace(".jpg", "");
            fileName = fileName.replace(".jpeg", "");
            fileName = fileName.replace(".gif", "");
            if (resFolder.trim().length() > 0) {
                resID = context.getResources().getIdentifier(fileName, resFolder, context.getPackageName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resID;
    }

    public static InputStream getStreamFromResID(Context context, int resId) {
        try {
            return context.getResources().openRawResource(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream getStreamFromAssetsFileName(Context context, String fileName) {
        try {
            return context.getResources().getAssets().open(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("NewApi")
    public static Bitmap getBitmapFromStream(InputStream stream, int intSampleSize) {
        try {
            BitmapFactory.Options m_Options = new BitmapFactory.Options();
            m_Options.inSampleSize = intSampleSize < 1 ? 1 : intSampleSize;
            m_Options.inPurgeable = true;
            Bitmap m_ReturnBitmap = BitmapFactory.decodeStream(stream, null, m_Options);
            stream.close();
            return m_ReturnBitmap;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}

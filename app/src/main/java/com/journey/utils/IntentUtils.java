package com.journey.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.os.RemoteException;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;
import android.widget.Toolbar;

import com.journey.MainTabsFA;
import com.journey.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/10.
 */
public class IntentUtils {

    /**
     * 调用系统浏览器
     * */
    public static void viewUrlInBrowser(Context ctx,String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(intent);
    }
    /**
     * 拨号界面
     * */
    public static void showCallView(Context ctx,String tel){
            Uri uri = Uri.parse("tel:"+tel);
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);  //与下面方式的不同
         //   Intent intent = new Intent(Intent.ACTION_CALL,uri); //这个需要权限 requires android.permission.CALL_PHONE
            ctx.startActivity(intent);
    }
    /**
     *
     * */
    public static void showCallApp(Context ctx){
        Intent intent= new Intent("android.intent.action.DIAL");
        intent.setClassName("com.android.contacts","com.android.contacts.DialtactsActivity");
        ctx.startActivity(intent);
    }
    /**
     * 通话记录
     * */
    public static void showTelListView(Context ctx){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL_BUTTON);
        ctx.startActivity(intent);
    }
    /**
     * 到联系人列表界面
     * */
    public static void showContactsView(Context ctx){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Contacts.People.CONTENT_URI);
        ctx.startActivity(intent);
    }
    /**
     * 到联系人应用
     * */
    public static void showContactsApp(Context ctx){
        Intent intent= new Intent("com.android.contacts.action.LIST_STREQUENT");
        intent.setClassName("com.android.contacts","com.android.contacts.DialtactsActivity");
        ctx.startActivity(intent);
    }
    public static void openContacts(Context ctx,int requsetCode){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("vnd.android.cursor.item/phone");
        ((Activity)ctx).startActivityForResult(i, requsetCode);

    }
    /**
     * 调用联系人界面，选择联系人
     * */
     public static void useContactsView(Context ctx,int requestCode){
//         Intent intent = new Intent();
//         intent.setAction(Intent.ACTION_PICK);
//         intent.setData(Contacts.People.CONTENT_URI);
//         ctx.startActivity(intent);

         Uri uri = Uri.parse("content://contacts/people");
         Intent intent = new Intent(Intent.ACTION_PICK, uri);
         ((Activity)ctx).startActivityForResult(intent, requestCode);

     }
    /**
     * 编辑联系人
     * */
    public static void editContacts(Context ctx){
        Intent intent=new Intent(Intent.ACTION_EDIT,Uri.parse("content://com.android.contacts/contacts/"+"1"));
        ctx.startActivity(intent);
    }
    /**
     * 添加联系人，新建 或者 添加到已有联系人
     * */
    public static void insertContacts(Context ctx,String name,String company,String tel){
        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        intent.setType("vnd.android.cursor.item/person");
        intent.setType("vnd.android.cursor.item/contact");
        intent.setType("vnd.android.cursor.item/raw_contact");
        intent.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, name);
        intent.putExtra(android.provider.ContactsContract.Intents.Insert.COMPANY,company);
        intent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE, tel);
        intent.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE_TYPE, 3);
        ctx.startActivity(intent);
    }
    /**
     * 从Sim卡或联系人列表中查询信息
     * type=1 从sim卡查询
     * */
    public static void checkFromSimOrContacts(Context ctx,int type){
        Cursor cursor;
        Uri uri;
        if (type == 1) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("content://icc/adn"));
            uri = intent.getData();
        } else
            uri = Contacts.People.CONTENT_URI;

        cursor = ctx.getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            int peopleId = cursor.getColumnIndex(Contacts.People._ID);
            int nameId = cursor.getColumnIndex(Contacts.People.NAME);
            int phoneId = cursor.getColumnIndex(Contacts.People.NUMBER);
        }

//        Uri personUri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, ID);//最后的ID参数为联系人Provider中的数据库BaseID，即哪一行
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.setData(personUri);
//        startActivity(intent);
    }
    /**
     * 删除联系人
     * @id 联系人的id
     * */
    public static void deleteContacts(Context ctx,int id){
        Uri uri = ContentUris.withAppendedId(Contacts.People.CONTENT_URI, id);
        int count = ctx.getContentResolver().delete(uri, null, null);
    }
    /**
     * 添加到联系人
     * */
    public void addToContacts(Context ctx){
        ContentValues cv = new ContentValues();
        ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation.Builder builder = ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
        builder.withValues(cv);
        operationList.add(builder.build());
        builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        builder.withValueBackReference(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID, 0);
        builder.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        builder.withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, "自定义联系人名");
        operationList.add(builder.build());
        builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
        builder.withValueBackReference(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, 0);
        builder.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, "联系人的phonenumber");
        builder.withValue(ContactsContract.Data.IS_PRIMARY, 1);
        operationList.add(builder.build());
        try {
            ctx.getContentResolver().applyBatch(ContactsContract.AUTHORITY, operationList);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    //http://blog.csdn.net/lilu_leo/article/details/6938729
    /**
     * 到编辑短信界面
     * */
    public static void showSMSView(Context ctx){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
//              intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
        ctx.startActivity(intent);
    }
    /**
     * 到短信应用
     * */
    public static void showSMSApp(Context ctx){
        Intent intent = new Intent("android.intent.action.CONVERSATION");
        ctx.startActivity(intent);
    }
    /**
     * 发送短信一
     * */
    public static void sendSMS1(Context ctx,String tel,String msg){
        Uri uri = Uri.parse("smsto:"+tel);
        Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
        ctx.startActivity(intent);
    }
    /**
     * 发送短信二
     * */
    public void sendSMS2(Context ctx,String tel,String msg){
        Intent intent = new Intent(Intent.ACTION_VIEW);
     //   intent.putExtra("address", c.getString(c.getColumnIndex(column)));
        intent.setType("vnd.android-dir/mms-sms");
        ctx.startActivity(intent);
    }
    /**
     * 发送短信三
     * */
    public static void sendSMS3(Context ctx,String tel,String msg){
        Intent mmsintent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", tel, null));
//        mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_BODY, msg);
//        mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_COMPOSE_MODE, true);
//        mmsintent.putExtra(Messaging.KEY_ACTION_SENDTO_EXIT_ON_SENT, true);
        ctx.startActivity(mmsintent);
    }
    /**
     * 添加到短信收件箱
     * */
    public static void addToMessageBox(Context ctx){
        ContentValues cv = new ContentValues();
        cv.put("type", "1");
        cv.put("address","短信地址");
        cv.put("body", "短信内容");
        ctx.getContentResolver().insert(Uri.parse("content://sms/inbox"), cv);
    }
    /**
     * 发送彩信
     * */
    public static void sendMMS(Context ctx,File file,String tel,String subject,String body){
        StringBuilder sb = new StringBuilder();
        sb.append("file://");
        sb.append(file.getAbsoluteFile());
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mmsto", tel, null));
// Below extra datas are all optional.
//        intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_SUBJECT, subject);
//        intent.putExtra(Messaging.KEY_ACTION_SENDTO_MESSAGE_BODY, body);
//        intent.putExtra(Messaging.KEY_ACTION_SENDTO_CONTENT_URI, sb.toString());
//        intent.putExtra(Messaging.KEY_ACTION_SENDTO_COMPOSE_MODE, composeMode);
//        intent.putExtra(Messaging.KEY_ACTION_SENDTO_EXIT_ON_SENT, exitOnSent);
        ctx.startActivity(intent);
    }

    /**
     * 发送邮件
     * */
   public static void sendEmail(Context ctx){
//       Uri uri = Uri.parse("mailto:xxx@abc.com");
//       Intent it = new Intent(Intent.ACTION_SENDTO, uri);
//       ctx.startActivity(it);
//
//       Intent it = new Intent(Intent.ACTION_SEND);
//       it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
//       it.putExtra(Intent.EXTRA_TEXT, "The email body text");
//       it.setType("text/plain");
//       ctx.startActivity(Intent.createChooser(it, "Choose Email Client"));

       Intent it=new Intent(Intent.ACTION_SEND);
       String[] tos={"me@abc.com"};
       String[] ccs={"you@abc.com"};
       it.putExtra(Intent.EXTRA_EMAIL, tos);
       it.putExtra(Intent.EXTRA_CC, ccs);
       it.putExtra(Intent.EXTRA_TEXT, "The email body text");
       it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
       it.setType("message/rfc822");
       ctx.startActivity(Intent.createChooser(it, "Choose Email Client"));

//       Intent it = new Intent(Intent.ACTION_SEND);
//       it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
//       it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
//       it.setType("audio/mp3");
//       ctx.startActivity(Intent.createChooser(it, "Choose Email Client"));
   }

      /**
     * 使用谷歌搜索
     * */
    public static void searchInGoogle(Context ctx){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY,"searchString");
        ctx.startActivity(intent);
    }
    /**
     * 显示地图
     * */
    public static void showMap(Context ctx){
        /*Uri uri = Uri.parse("geo:38.899533,-77.036476");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        ctx.startActivity(it);*/  //android.content.ActivityNotFoundException: No Activity found to handle Intent
        try{
            //直接调用百度地图
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("geo:39.922840,116.3543240,北京市西城区阜外大街2号万通大厦");
            intent.setData(uri);
            intent.setPackage("com.baidu.BaiduMap");
            ctx.startActivity(intent);
        }catch(Exception ex) {
           /* //使用图吧地图引擎开发
           Intent intent = new Intent();
            intent.putExtra("lat", "39.922840");
            intent.putExtra("lon", "116.3543240");
            intent.putExtra("address", "北京市西城区阜外大街2号万通大厦");
            intent.setClass(MapbarDemo.this, IntentOverlayDemo.class);*/
            //直接调用图吧地图
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("geo:39.922840,116.3543240,北京市西城区阜外大街2号万通大厦");
            intent.setData(uri);
            intent.setClassName("com.mapbar.android.mapbarmap", "com.mapbar.android.mapbarmap.FilterServiceActivity");
            ctx.startActivity(intent);
        }
    }
    /**
     * 路径规划,手机暂时访问不了google相关链接
     * */
    public static void planRoute(Context ctx){
        Uri uri = Uri.parse("http://maps.google.com/maps?f=dsaddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        ctx.startActivity(it);
    }

    /**
     * 播放多媒体
     * */
    public static void playMedia(Context ctx){
//        Intent it = new Intent(Intent.ACTION_VIEW);
//        Uri uri = Uri.parse("file:///sdcard/song.mp3");
//        it.setDataAndType(uri, "audio/mp3");
//        ctx.startActivity(it);
        Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(it); // android.content.ActivityNotFoundException: No Activity found to handle Intent
    }

    /**
     * 卸载apk
     * */
    public static void uninstallApk(Context ctx,String strPackageName){
        Uri uri = Uri.fromParts("package", strPackageName, null);
        Intent it = new Intent(Intent.ACTION_DELETE, uri);
        ctx.startActivity(it);
    }
    /**
     * 安装apk
     * */
    public static void installApk(Context ctx,String strPackageName){
        Uri installUri = Uri.fromParts("package", strPackageName, null);
        Intent intent = new Intent(Intent.ACTION_PACKAGE_ADDED, installUri);
        ctx.startActivity(intent);
    }
    /**
     * 打开照相机
     * */
   public static void openCamera(Context ctx,int requestCode){
  //     Intent i = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
  //     ctx.sendBroadcast(i);

       long dateTaken = System.currentTimeMillis();
      // String name = createName(dateTaken) + ".jpg";
       String name = dateTaken + ".jpg";
       String fileName = "wm/" + name;
       ContentValues values = new ContentValues();
       values.put(MediaStore.Images.Media.TITLE, fileName);
       values.put("_data", fileName);
       values.put(MediaStore.Images.Media.PICASA_ID, fileName);
       values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
       values.put(MediaStore.Images.Media.DESCRIPTION, fileName);
       values.put(MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME, fileName);
       Uri photoUri = ctx.getContentResolver().insert(
               MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

       Intent inttPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       inttPhoto.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
       ((Activity)ctx).startActivityForResult(inttPhoto, requestCode);
   }
    /**
     * 调用相机，并设置图片保存位置
     * */
    public static void useCamera(Context ctx,String filename,int requestCode){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mm", filename + ".jpg"))); //存放位置为sdcard卡上cwj文件夹，文件名为android123.jpg格式
        ((Activity)ctx).startActivityForResult(intent, requestCode);
    }

    public static void choosePictures(Context ctx,int requestCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        ((Activity)ctx).startActivityForResult(intent, requestCode);
    }

    public static void choosePictures2(Context ctx,int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((Activity)ctx).startActivityForResult(intent, requestCode);
    }

   /**
    * 从gallery选取图片
    * */
    public static void pickPicturesFormGallery(Context ctx,int requestCode){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity)ctx).startActivityForResult(i, requestCode);
    }
    /**
     * 打开录音机
     * */
    public static void openRecorder(Context ctx){
        Intent mi = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        ctx.startActivity(mi);
    }

    /**
     * 打开market上，某应用的详细列表
     * */
    public static void showAppList(Context ctx,String pakageName){
        Uri uri = Uri.parse("market://details?id="+pakageName);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(it);
    }

    /**
     *在market上搜索指定package name
     * */
    public static void searchApp(Context ctx,String pkg_name){
        Uri uri = Uri.parse("market://search?q=pname:"+pkg_name);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        ctx.startActivity(it);
    }
    /**
     * 打开其他的应用程序
     * */
    public static void openOtherApp(Context ctx,String pkg_name,int requestCode){
        Intent i = new Intent();
        ComponentName cn = new ComponentName("com.fx678.finace",
                "com.fx678.finace.activitys.LogoA");
        i.setComponent(cn);
        i.setAction("android.intent.action.MAIN");
        ((Activity)ctx).startActivityForResult(i, requestCode);
    }
    /**
     * 创建快捷方式
     * */
    public static void addShortCuts(Context ctx){
        Intent addIntent = new Intent();
     //   addIntent.setAction(Intent.ACTION_CREATE_SHORTCUT);//不起作用
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");//ok
        Parcelable icon = Intent.ShortcutIconResource.fromContext(ctx, R.drawable.ic_launcher);
        Intent myIntent = new Intent(ctx, MainTabsFA.class); //点击快捷方式后的Intent

        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,"Journey");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,icon);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,myIntent);
        ctx.sendBroadcast(addIntent);
        Toast.makeText(ctx,"添加快捷方式成功！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 使用相应软件打开
     * */
    private static void openFile(Context ctx,File f)
    {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        String type = getMIMEType(f);
        intent.setDataAndType(Uri.fromFile(f), type);
        ctx.startActivity(intent);
    }

    private static String getMIMEType(File f){
        String end = f
                .getName()
                .substring(f.getName().lastIndexOf(".") + 1,
                        f.getName().length()).toLowerCase();
        String type = "";
        if (end.equals("mp3") || end.equals("aac") || end.equals("aac")
                || end.equals("amr") || end.equals("mpeg")
                || end.equals("mp4"))
        {
            type = "audio";
        } else if (end.equals("jpg") || end.equals("gif")
                || end.equals("png") || end.equals("jpeg"))
        {
            type = "image";
        } else
        {
            type = "*";
        }
        type += "/*";
        return type;
    }
    /**
     * ES 文件浏览器发布 PICK_FILE, PICK_DIRECTORY 和 extras 三个接口，供开发者调用，实现“打开文件”、“选择目录”功能。
     * */
    public static void openESFileBrowser(Context ctx,int requestCode){
        Intent intent = new Intent("com.estrongs.action.PICK_FILE ");
        intent.putExtra("com.estrongs.intent.extra.TITLE", "Select File");

        ((Activity)ctx).startActivityForResult(intent, requestCode);
     //   ctx.startActivity(intent);
    }

    @SuppressLint("NewApi")
    public static void startAnotherApp(Context context, String packageName, String targetActivity,int requestCode) {
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
                ((Activity) context).startActivityForResult(intent, requestCode);
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

    public static void chooseRington(Context ctx,String title,int type,int requestCode){
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, title);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, type);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        ((Activity)ctx).startActivityForResult(intent, requestCode);
    }



}

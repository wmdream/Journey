package com.journey.receiver;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.journey.MainTabsFA;
import com.journey.R;
import com.journey.activity.NotificationA;
import com.journey.utils.PrefsSettingUtils;

/**
 * Created by Administrator on 2015/3/16.
 */
public class MyReceiver extends BroadcastReceiver {

    Uri uri;
    @Override
    public void onReceive(Context context, Intent intent) {
        int mNotificationId = 131617;
        Intent resultIntent = new Intent(context, MainTabsFA.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //可以设置多个Intent，用户自行选择 跳转哪个Intent，界面
        Intent msgIntent = new Intent(context, NotificationA.class);
        msgIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent msgPendingIntent = PendingIntent.getActivity(context, 0, msgIntent, 0);

//        String notifysounds = PrefsSettingUtils.getLive_NotifySounds(this);
//        if (!"".equals(notifysounds)) {
//            uri = Uri.parse(notifysounds);
//        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)

                //    .setSmallIcon(R.drawable.ic_ht_notify)
                .setSmallIcon(R.drawable.ic_launcher)
               // .setSound(Uri.parse("file///sdcard/click.mp3"))
               // .setVibrate()
               // .setLights()
                .setTicker("BroadcastReceiver")

                .setContentTitle("通知")

                .setContentText("测试Notification")

                .setContentIntent(resultPendingIntent).setAutoCancel(true)

                .setDefaults(Notification.DEFAULT_ALL)

                .addAction(R.drawable.ic_launcher, "主界面", resultPendingIntent)

                .addAction(R.drawable.ic_launcher, "Notification子界面", msgPendingIntent)

                .setStyle(new NotificationCompat.BigTextStyle().bigText("顶部推送通知")).setAutoCancel(true);

        Notification noti = mBuilder.build();
        noti.ledARGB = Color.BLUE;
        noti.ledOnMS = 300;
        noti.ledOffMS = 1000;
        noti.flags |= Notification.FLAG_SHOW_LIGHTS;

        noti.defaults = Notification.DEFAULT_SOUND;
        String notifysounds = PrefsSettingUtils.getLive_NotifySounds(context);
        if (!"".equals(notifysounds)) {
            Uri uri = Uri.parse(notifysounds);
            noti.sound = uri;
        }


        NotificationManagerCompat mNotifyMgr = (NotificationManagerCompat) NotificationManagerCompat.from(context);
        mNotifyMgr.notify(mNotificationId, noti);
    }
}

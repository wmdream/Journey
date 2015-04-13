package com.journey.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.journey.MainTabsFA;
import com.journey.R;
import com.journey.data.Const;
import com.journey.interfaces.IBaseA;
import com.journey.utils.IntentUtils;
import com.journey.utils.PrefsSettingUtils;
import com.journey.utils.ToolbarUtils;

public class NotificationA extends ActionBarActivity implements IBaseA{

    public static final int CHOOSE_NOTIFICATION_SOUND = 1;
    private TextView tv_notification_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ToolbarUtils.setSystemToolbar((ActionBarActivity)getCtx());
        tv_notification_sound = (TextView)findViewById(R.id.tv_notification_sounds);
        String name = PrefsSettingUtils.getLive_NotifySoundsName(this);
        if(!name.equals("")){
            tv_notification_sound.setText(name);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home: // 对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.notification_send:
                sendStaticBroadcast();
                break;
            case R.id.notification_choose_ringtone:
                IntentUtils.chooseRington(getCtx(),"选择铃声",RingtoneManager.TYPE_NOTIFICATION,CHOOSE_NOTIFICATION_SOUND);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case CHOOSE_NOTIFICATION_SOUND:
                    Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
                    if (uri != null) {
                        String ringTonePath = uri.toString();
                        PrefsSettingUtils.setLive_NotifySounds(this, ringTonePath);
                        Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
                        String name = ringtone.getTitle(this);
                        tv_notification_sound.setText(name);
                        PrefsSettingUtils.setLive_NotifySoundsName(this, name);
                        break;
                    }
            }
        }
    }

    private void sendStaticBroadcast(){
         Intent intent_notify = new Intent();
         intent_notify.setAction(Const.ACTION_APR);
         sendBroadcast(intent_notify);
    }

    @Override
    public Context getCtx() {
        return this;
    }

    /*private void sendNonStaticBroadcast(){
        Intent intent_notify = new Intent();
        intent_notify.setAction(Const.ACTION_APR);
        sendBroadcast(intent_notify);
    }

    class InnerBroadcastReceiver extends BroadcastReceiver{

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

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)

                    //    .setSmallIcon(R.drawable.ic_ht_notify)
                    .setSmallIcon(R.drawable.ic_launcher)

                    .setTicker("广播")

                    .setContentTitle("顶部推送通知")

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

            NotificationManagerCompat mNotifyMgr = (NotificationManagerCompat) NotificationManagerCompat.from(context);
            mNotifyMgr.notify(mNotificationId, noti);
        }
    }*/




}

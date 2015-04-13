package com.journey.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.journey.interfaces.IBaseA;

/**
 * Created by Administrator on 2015/3/16.
 */

/**
 * startService():访问者与Service没有关联，访问者退出了，Service仍然运行，适合开启 永久性Service
 * bindService() :访问者与Service绑定在一起，访问者退出了，Service也就终止了，适合开启 依附于应用程序 或 某个Activity的Service
 * 使用bindService()开启服务的，主要用于 与service的交互，如 获取后台下载进度等，Service将不执行onStartCommand()方法
 * 本MyService主要练习bindService, 资源下载
 * */
public class MyService extends Service implements IBaseA{

    private MyBinder binder = new MyBinder();

    @Override
    public Context getCtx() {
        return this;
    }

    public class MyBinder extends Binder {
        public Context getService(){
            return getCtx();
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
      //  return START_STICKY;
  //      return START_REDELIVER_INTENT;
    }

    class DownloadTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }
    }
}

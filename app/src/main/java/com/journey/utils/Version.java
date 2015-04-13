package com.journey.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.journey.data.Const;

/**
 * 版本相关
 *
 * @author Watson Yao
 */
public class Version {

    private boolean toast0 = false;
    private Context context;

    /**
     * 版本检查
     *
     * @param context
     * @param toast0  true显示tost
     */
    public void check(Context context, boolean toast0) {
        MyTask myTask = new MyTask();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            myTask.execute();
        }
        this.toast0 = toast0;
        this.context = context;
    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String version = "0";
            version = new Nettools().getStrFromURL(Const.APP_VersionCheckURL);

            return version;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            showDialog(result);

        }
    }

    private void showDialog(final String result) {
        try {
            String v = result.substring(0, 1);
            //MyApplication.htlog.i("result->" + result);
            // v = "1"; // 测试使用
            Builder builder = new Builder(context);
            final Intent intent1 = new Intent(Intent.ACTION_VIEW);

            if ("1".equals(v)) {
                builder.setTitle("发现新版本，是否更新？").setPositiveButton("下载更新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.setData(Uri.parse(result.substring(2, result.length())));
                        context.startActivity(intent1);
                    }
                }).setNegativeButton("不更新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

            } else if ("2".equals(v)) {
                builder.setTitle("请更新到最新版使用！").setPositiveButton("下载更新", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        intent1.setData(Uri.parse(result.substring(2, result.length())));
                        context.startActivity(intent1);
                    }
                }).setCancelable(false).create().show();

            } else if ("0".equals(v)) {
                if (toast0) {
                    Toast.makeText(context, "已是最新版本", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
        }
    }

}

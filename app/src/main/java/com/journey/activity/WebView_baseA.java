package com.journey.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import com.journey.R;
import com.journey.data.Const;
import com.journey.utils.ToolbarUtils;
import com.journey.utils.WebViewBase;

public class WebView_baseA extends ActionBarActivity {

    private WebView webview;
    private TextView title;
    private String url;
    private String title_text;
    private String come4;
    private String content_html;

    // ///////////////////////////////
    // //广告相关
    // ///////////////////////////////
    private String ad_title;
    private TextView openAD;
    private SharedPreferences sharedPreferences2;
    private String open_url;
    private String selected;

    @Override
    protected void onStart() {
        super.onStart();
        // 谷歌分析统计代码

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void onResume() {
        super.onResume();
       //友盟统计
    }

    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_a);

        ToolbarUtils.setNoTileToolbar(this);

        url = getIntent().getExtras().getString(Const.WEBVIEW_URL);
        title_text = getIntent().getExtras().getString(Const.WEBVIEW_TITLE);
        come4 = getIntent().getExtras().getString(Const.COMEFROM);
        content_html = getIntent().getExtras().getString(Const.HTML);


        title = (TextView) findViewById(R.id.title);
        try {
            int size = title_text.length();
            if (size > 10) {
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            }
        } catch (Exception e) {
        }

        title.setText(title_text);


        openAD = (TextView) findViewById(R.id.openAD);
        /*sharedPreferences2 = getSharedPreferences(Const.HT_AD, MODE_PRIVATE);

        if ("金牌分析师".equals(title_text)) {
            selected = ADConst.ADVERT_GOLD_ANALYST;
        } else if ("汇通答疑".equals(title_text)) {
            selected = ADConst.ADVERT_ANSWER;;
        } else {
            selected = "";
        }


        // 判断广告是否需要显示 以及显示什么样子的广告
        openAD.setVisibility(View.GONE);
        ad_title = "";

        if (selected.equals(sharedPreferences2.getString(ADConst.KEY_KEY + selected, "")) && !"".equals(selected)) {
            openAD.setVisibility(View.VISIBLE);
            open_url = sharedPreferences2.getString(ADConst.KEY_URL + selected, "");
            ad_title = sharedPreferences2.getString(ADConst.KEY_TITLE + selected, "广告位");
            openAD.setText(ad_title);
        }*/

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
                    return true;
                } else if (url.startsWith("mailto:")) {
                    url = url.replaceFirst("mailto:", "");
                    url = url.trim();
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("plain/text").putExtra(Intent.EXTRA_EMAIL, new String[]{url});
                    startActivity(i);
                    return true;
                } else if (url.startsWith("geo:")) {
                    Intent searchAddress = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(searchAddress);
                    return true;
                } else {
                    view.loadUrl(url);
                    return true;
                }
            }
        });

        webview.setWebChromeClient(new MyWebChromeClient());
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setDownloadListener(new MyWebViewDownLoadListener());

        webview.post(new Runnable() {
            @Override
            public void run() {

                try {
                    if(come4!=null && Const.HTML.equals(come4)){
                        webview.loadData(content_html, "text/html; charset=UTF-8", null);
                    }else{
                        webview.loadUrl(url);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    // 系统的back回退键重写，不会退处Activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    final Context myApp = this;

    final class MyWebChromeClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(myApp).setTitle("提示：").setMessage(message)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).create().show();
            return true;
        }

        ;

        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            new AlertDialog.Builder(myApp).setTitle("提示：").setMessage(message)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            result.confirm();
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    result.cancel();
                }
            }).create().show();

            return true;
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home: // 对用户按home icon的处理，本例只需关闭activity，就可返回上一activity，即主activity。
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 右上角的广告点击及统计
     *
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openAD:
                /*if (!"".equals(selected)) {

                }
                WebViewBase.open(this, open_url, ad_title);*/
                break;

            default:
                break;
        }
    }

}

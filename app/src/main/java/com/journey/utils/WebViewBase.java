package com.journey.utils;

import android.content.Context;
import android.content.Intent;

import com.journey.activity.WebView_baseA;
import com.journey.data.Const;


public class WebViewBase {
	public static void open(Context context, String url, String title) {
        if(!"".equals(title) && title != null){
            Intent intent_base = new Intent(context, WebView_baseA.class);
            intent_base.putExtra(Const.WEBVIEW_URL, url);
            intent_base.putExtra(Const.WEBVIEW_TITLE, title);
            context.startActivity(intent_base);
        }
	}
}

package com.journey.interfaces;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;

/**
 * Created by Administrator on 2015/2/3.
 */
public interface IPresenter {
    public void showDialog(String title,String[] items,DialogInterface.OnClickListener listener);

}

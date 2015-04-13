package com.journey.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.journey.interfaces.IPresenter;

/**
 * Created by Administrator on 2015/3/2.
 */
public class Presenter implements IPresenter {

    Context context;

    public Presenter(Context context){
        this.context = context;
    }

    public void showDialog(String title,String[] items,DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(items,listener)
                .setCancelable(true)
                .create()
                .show();
    }
}

package com.journey.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.journey.R;
import com.journey.interfaces.IBaseA;
import com.journey.interfaces.IPresenter;
import com.journey.presenter.Presenter;
import com.journey.utils.ImageTools;
import com.journey.utils.ToolbarUtils;

public class ActivityCompA extends ActionBarActivity implements IBaseA{

    private IPresenter presenter;
    private ImageTools imageTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp);
        ToolbarUtils.setSystemToolbar(this);
        presenter = new Presenter(getCtx());
        imageTools = new ImageTools();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_comp, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.activity_load_model:
                showLoadModel();
                break;
            case R.id.activity_life_circle:
                showLifeImage();
                break;
            case R.id.activity_fragment:
                break;
            default:
                break;
        }
    }

    @Override
    public Context getCtx(){
        return this;
    }

    private void showLoadModel(){
        presenter.showDialog(getResources().getString(R.string.activity_load_model),
                getResources().getStringArray(R.array.activity_load_model2),null);
    }

    private void showLifeImage(){
        View view = findViewById(R.id.layout_main);
        imageTools.showPopupWindow(getCtx(),view,R.drawable.activity_lifecycle);
    }

}

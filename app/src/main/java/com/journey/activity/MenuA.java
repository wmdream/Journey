package com.journey.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.journey.MyApplication;
import com.journey.R;
import com.journey.interfaces.IBaseA;
import com.journey.presenter.Presenter;
import com.journey.utils.DialogUtil;
import com.journey.utils.ToolbarUtils;

public class MenuA extends ActionBarActivity implements IBaseA{

    private Presenter presenter;
    private TextView tv_action_mode,tv_normal;
    private ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ToolbarUtils.setSystemToolbar(this);
        presenter = new Presenter(getCtx());
        tv_action_mode = (TextView)findViewById(R.id.menu_action_mode);
        tv_action_mode.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView
            public boolean onLongClick(View view) {
               /* if (mActionMode != null) {
                    return false;
                }*/
               // MyApplication.logger.i("setOnLongClickListener 1 ->"+mActionMode==null);
                mActionMode = ((Activity)getCtx()).startActionMode(mActionModeCallback);
                view.setSelected(true);
              //  MyApplication.logger.i("setOnLongClickListener 2 ->"+mActionMode==null);
                return true;
            }
        });

        tv_normal = (TextView)findViewById(R.id.menu_normal);
        registerForContextMenu(tv_normal);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){

    }

    @Override
    public Context getCtx() {
        return this;
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_action_mode, menu);

            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            boolean ret = false;
            if (item.getItemId() == R.id.action_delete) {
                DialogUtil.showTitle(getCtx(),"选择删除");
                ret = true;
            }
            if (item.getItemId() == R.id.action_cancel) {
                mode.finish();
               // MyApplication.logger.i("actionMode==null ->"+mActionMode==null);
                ret = true;
            }
            return ret;
        }
    };

    /**
     *  服务于 registerForContextMenu(tv_normal);
     * */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_action_mode, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cancel:
              //  DialogUtil.showTitle(getCtx(),"选择取消");
                break;
            case R.id.action_delete:
                DialogUtil.showTitle(getCtx(),"选择删除");
                break;
        }
        return super.onContextItemSelected(item);
    }
}

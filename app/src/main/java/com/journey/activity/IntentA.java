package com.journey.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.journey.MyApplication;
import com.journey.R;
import com.journey.interfaces.IBaseA;
import com.journey.interfaces.IPresenter;
import com.journey.presenter.Presenter;
import com.journey.utils.IntentUtils;
import com.journey.utils.ToolbarUtils;
/**
 * 调用系统：浏览器、文件浏览、短信、电话、Email、SMS、音乐、视频、日历、时钟
 * */
public class IntentA extends ActionBarActivity implements IBaseA{

    IPresenter presenter;
    public static final int REQUEST_CODE_OPEN_IMAGE = 1;
    public static final int REQUEST_CODE_OPEN_FILE_BROWSWE = 2;
    public static final int REQUEST_CODE_PICK_PICTURES = 3;
    public static final int REQUEST_CODE_OPEN_CAMERA = 4;
    public static final int REQUEST_CODE_PICK_FILE_TO_OPEN = 5;
    public static final int REQUEST_CODE_OPEN_OTHER_APP = 6;
    public static final int REQUEST_CODE_OPEN_CONTACTS = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        ToolbarUtils.setSystemToolbar(this);
        presenter = new Presenter(getCtx());
    }

    @Override
    public Context getCtx(){
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intent, menu);
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
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v){
        try{
        switch (v.getId()){
            case R.id.intent_browser:
               IntentUtils.viewUrlInBrowser(getCtx(), "http://www.baidu.com");
                break;
            case R.id.intent_contacts:
           //     IntentUtils.showContactsView(getCtx());
        //        IntentUtils.showContactsApp(getCtx()); //error
          //      IntentUtils.useContactsView(getCtx(),REQUEST_CODE_OPEN_CONTACTS);//
                IntentUtils.insertContacts(getCtx(),"HELLO","WORLD","10088");
         //       IntentUtils.editContacts(getCtx());
                break;
            case R.id.intent_telephone:
                IntentUtils.showCallView(getCtx(),"10088888");//拨号界面
         //      IntentUtils.showTelListView(getCtx()); //通话记录
          //      IntentUtils.showCallApp(getCtx());//errors

                break;
            case  R.id.intent_sms:
          //      IntentUtils.showSMSView(getCtx());
          //      IntentUtils.showSMSApp(getCtx());//error
                IntentUtils.sendSMS1(getCtx(),"1000000000","HELLO");
             //   IntentUtils.sendMMS();
                break;
            case R.id.intent_email:
                IntentUtils.sendEmail(getCtx());
                break;
            case R.id.intent_map:
                IntentUtils.showMap(getCtx());
          //      IntentUtils.searchInGoogle(getCtx());
         //       IntentUtils.planRoute(getCtx());
                break;
            case R.id.intent_music:
         //       IntentUtils.playMedia(getCtx());//error
         //       IntentUtils.openRecorder(getCtx());//error
                break;
            case R.id.intent_camera:
          //      IntentUtils.openCamera(getCtx(),REQUEST_CODE_OPEN_IMAGE);
           //     IntentUtils.useCamera(getCtx(),"hello",REQUEST_CODE_OPEN_IMAGE);
           //     IntentUtils.pickPicturesFormGallery(getCtx(),REQUEST_CODE_OPEN_IMAGE);
                IntentUtils.choosePictures(getCtx(),REQUEST_CODE_OPEN_IMAGE); //ok
        //        IntentUtils.choosePictures2(getCtx(),REQUEST_CODE_OPEN_IMAGE); //ok

                break;
            case R.id.intent_file:
                //文件浏览器:ES文件浏览器，普通文件浏览器
     //           IntentUtils.openESFileBrowser(getCtx(),REQUEST_CODE_PICK_FILE_TO_OPEN);//error
                break;
            case R.id.intent_app:
            //    IntentUtils.uninstallApk(getCtx(),"com.fx678.finace");
         //       IntentUtils.installApk(getCtx(),"com.fx678.finace");s
         //       IntentUtils.openOtherApp(getCtx(),"com.fx678.finace",REQUEST_CODE_OPEN_OTHER_APP);
                IntentUtils.addShortCuts(getCtx());
                break;
            case R.id.intent_market:
         //       IntentUtils.searchApp(getCtx(),"com.fx678.finace");
                IntentUtils.showAppList(getCtx(),"com.fx678.finace");
                break;
            default:
                break;
        }
        }catch(Exception e){
            MyApplication.logger.i("Exception->"+e.toString());
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            switch (requestCode){
                case REQUEST_CODE_OPEN_CAMERA:
                    break;
                case REQUEST_CODE_OPEN_FILE_BROWSWE:
                    break;
                case REQUEST_CODE_OPEN_IMAGE:
                    //这个uri，就是返回的选中的图片
                    Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getColumnCount(); i++)
                    {// 取得图片uri的列名和此列的详细信息
                       MyApplication.logger.i(i + "-" + cursor.getColumnName(i) + "-" + cursor.getString(i));
                    }
                    break;
                case REQUEST_CODE_PICK_PICTURES:
                    break;
                case REQUEST_CODE_PICK_FILE_TO_OPEN:
                  //  Uri uri = data.getData();
                    if (uri != null) {
                        Toast.makeText(this,  "文件->" + uri.getPath(), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case REQUEST_CODE_OPEN_OTHER_APP:
                    break;
                default:
                    break;
            }
        }

    }
}

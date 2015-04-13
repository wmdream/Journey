package com.journey.activity;

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.journey.R;
import com.journey.interfaces.IBaseA;
import com.journey.utils.ToolbarUtils;

public class AudioSoundsA extends ActionBarActivity implements IBaseA {

    AudioManager audioManager;
    MediaPlayer player;
    ToggleButton toggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sounds);
        ToolbarUtils.setSystemToolbar(this);
        audioManager = (AudioManager)getSystemService(Service.AUDIO_SERVICE);
        toggleButton = (ToggleButton)findViewById(R.id.button_toggle);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                audioManager.setStreamMute(AudioManager.STREAM_MUSIC,isChecked);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio_sounds, menu);
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

    @Override
    public Context getCtx() {
        return this;
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_start:
                player = MediaPlayer.create(getCtx(),R.raw.house);
                player.setLooping(true);
                player.start();
                break;
            case R.id.button_stop:
                if(player!=null){
                    player.stop();
                }
                break;
            case R.id.button_sound_up:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
                break;
            case R.id.button_sound_down:
                audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
                break;

            default:
                break;
        }
    }
}

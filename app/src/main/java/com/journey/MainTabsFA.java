package com.journey;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.journey.data.Const;
import com.journey.fragments.TabCenterF;
import com.journey.fragments.TabHomeF;
import com.journey.fragments.TabRightF;
import com.journey.interfaces.OnFragmentInteractionListener;
import com.journey.utils.Animations;


public class MainTabsFA extends FragmentActivity implements OnFragmentInteractionListener{

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    /**
     * Fragment界面列表
     */
    private Class fa_class[] = {TabHomeF.class, TabCenterF.class, TabRightF.class};
    /** tabhost 按钮列表 */
    private int fa_image[] = { R.drawable.tab_image_home, R.drawable.tab_image_explore, R.drawable.tab_image_individual };
    /**
     * tabhost 文字列表
     */
    private String fa_title[] ;//= {"首页", "发现", "我"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa_title = getResources().getStringArray(R.array.tab_titles);
        MyApplication.logger.i("fa_title->"+fa_title);
        initBottomTabs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
    View currentView;
    View currentView_old;
    private void initBottomTabs(){
        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fragment_container);

        int count = fa_class.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(fa_title[i]).setIndicator(getTabItemView(i));
            Bundle args = new Bundle();
            mTabHost.addTab(tabSpec, fa_class[i], args);
            // 设置Tab按钮的背景
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.action_search);
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                currentView = mTabHost.getCurrentView();
                currentView.setAnimation(Animations.inFromRightAnimation());
                if (currentView_old != null) {
                    currentView_old.setAnimation(Animations.outToRightAnimation());
                }
                currentView_old = currentView;
            }
        });

    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_title_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_title);
        imageView.setImageResource(fa_image[index]);

        TextView textView = (TextView) view.findViewById(R.id.tv_title);
        textView.setText(fa_title[index]);

        return view;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    long timeOfClickSystemBack = 0;
    @Override
    public void onBackPressed() {
        if (timeOfClickSystemBack != 0 && SystemClock.uptimeMillis() - timeOfClickSystemBack < Const.EXIT_APP_TIME) {
            this.finish();
        }else {
            timeOfClickSystemBack = SystemClock.uptimeMillis();
            Toast.makeText(this, getString(R.string.exit_app_tips), Toast.LENGTH_SHORT).show();
            return;
        }
        super.onBackPressed();
    }
}

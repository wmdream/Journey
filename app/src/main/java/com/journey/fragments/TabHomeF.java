package com.journey.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.journey.R;
import com.journey.activity.ActivityCompA;
import com.journey.activity.AudioSoundsA;
import com.journey.activity.BroadcastCompA;
import com.journey.activity.IntentA;
import com.journey.activity.MenuA;
import com.journey.activity.NotificationA;
import com.journey.activity.ServiceCompA;
import com.journey.interfaces.IPresenter;
import com.journey.interfaces.OnFragmentInteractionListener;
import com.journey.presenter.Presenter;
import com.journey.utils.DialogUtil;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabHomeF#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabHomeF extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView btn_tv_primary,btn_tv_high;
    private LinearLayout ll_primary_level,ll_high_level;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private IPresenter presenter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabHomeF.
     */
    // TODO: Rename and change types and number of parameters
    public static TabHomeF newInstance(String param1, String param2) {
        TabHomeF fragment = new TabHomeF();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TabHomeF() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.tab_home, container, false);
        presenter = new Presenter(getCtx());
        initView(view);
        return view;
    }

    private void initView(View view){
        btn_tv_primary = (TextView)view.findViewById(R.id.btn_tv_primary);
        btn_tv_high = (TextView)view.findViewById(R.id.btn_tv_high);
        ll_primary_level = (LinearLayout)view.findViewById(R.id.ll_primary_level);
        ll_high_level = (LinearLayout)view.findViewById(R.id.ll_high_level);
        btn_tv_primary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrimary();
            }
        });
        btn_tv_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHigh();
            }
        });
        showPrimary();
        initOnClick(view);
    }

    private void initOnClick(View view){

        view.findViewById(R.id.android_four_component).setOnClickListener(this);
        view.findViewById(R.id.android_data_read_write).setOnClickListener(this);
        view.findViewById(R.id.android_media_practice).setOnClickListener(this);
        view.findViewById(R.id.android_sdk_explore).setOnClickListener(this);
        view.findViewById(R.id.android_intent).setOnClickListener(this);
        view.findViewById(R.id.android_layout_component).setOnClickListener(this);
        view.findViewById(R.id.android_view_component_practice).setOnClickListener(this);
        view.findViewById(R.id.android_aidl).setOnClickListener(this);
        view.findViewById(R.id.android_style_theme).setOnClickListener(this);

        view.findViewById(R.id.android_develop_with_html5).setOnClickListener(this);
        view.findViewById(R.id.android_animation).setOnClickListener(this);
        view.findViewById(R.id.android_2d_3d_opengl).setOnClickListener(this);
        view.findViewById(R.id.android_google_maps).setOnClickListener(this);
        view.findViewById(R.id.android_sensor_practice).setOnClickListener(this);
        view.findViewById(R.id.android_ndk).setOnClickListener(this);
        view.findViewById(R.id.android_apk_decompiling).setOnClickListener(this);
        view.findViewById(R.id.android_useful_tools).setOnClickListener(this);
        view.findViewById(R.id.design_model).setOnClickListener(this);
        view.findViewById(R.id.data_structure).setOnClickListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void showPrimary(){
        btn_tv_primary.setBackgroundResource(R.drawable.tag_left_1);
        btn_tv_primary.setTextColor(getResources().getColor(R.color.color_white));
        btn_tv_high.setBackgroundResource(R.drawable.tag_right_0);
        btn_tv_high.setTextColor(getResources().getColor(R.color.primary_light));
        ll_primary_level.setVisibility(View.VISIBLE);
        ll_high_level.setVisibility(View.GONE);
        btn_tv_primary.setClickable(false);
        btn_tv_high.setClickable(true);
    }

    private void showHigh(){
        btn_tv_primary.setBackgroundResource(R.drawable.tag_left_0);
        btn_tv_primary.setTextColor(getResources().getColor(R.color.primary_light));
        btn_tv_high.setBackgroundResource(R.drawable.tag_right_1);
        btn_tv_high.setTextColor(getResources().getColor(R.color.color_white));

        ll_primary_level.setVisibility(View.GONE);
        ll_high_level.setVisibility(View.VISIBLE);
        btn_tv_primary.setClickable(true);
        btn_tv_high.setClickable(false);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.android_four_component:
                showFourComponent();
                break;
            case R.id.android_data_read_write:
                showDataReadWrite();
                break;
            case R.id.android_media_practice:
                showMediaPractice();
                break;
            case R.id.android_view_component_practice:
                showPracticalViewComponent();
                break;
            case R.id.android_intent:
                startActivity(new Intent(getCtx(), IntentA.class));
                break;
            case R.id.android_aidl:
                /*
                * Android Interface Definition Language (AIDL)
                * 用于IPC interprocess communication  进程间通信，跨进程通信
                * */
                DialogUtil.showTitleAndContent(getCtx(),"AIDL","Android Interface Definition Language \n" +
                        "用于IPC(interprocess communication)进程间通信,跨进程通信");
                 break;
            case R.id.android_ndk:
                DialogUtil.showTitleAndContent(getCtx(),"NDK", "Native Development Kit原生开发工具,\n 使得在android中,java可以调用C函数库");
                break;
            case R.id.design_model:
                showDesignModel();
                break;
            case R.id.data_structure:
                break;
            case R.id.android_sdk_explore:
                showSDKExplore();
                break;
            default:
                break;
        }
    }

    public Context getCtx(){
        return getActivity();//getActivity().getApplication().getApplicationContext()
    }

    public void showFourComponent() {
        presenter.showDialog(getResources().getString(R.string.android_four_component),
                getResources().getStringArray(R.array.android_four_component2),
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        startActivity(new Intent(getCtx(), ActivityCompA.class));
                        return;
                    case 1:
                        startActivity(new Intent(getCtx(), ServiceCompA.class));
                        return;
                    case 2:
                        startActivity(new Intent(getCtx(), BroadcastCompA.class));
                        return;
                    case 3:
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void showPracticalViewComponent(){
        presenter.showDialog(getResources().getString(R.string.android_view_component_practice),
                getResources().getStringArray(R.array.android_view_component_practice2),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                return;
                            case 1:
                                startActivity(new Intent(getCtx(), NotificationA.class));
                                return;
                            case 2:
                                return;
                            case 3:
                                return;
                            case 4:
                                return;
                            case 5:
                                return;
                            case 6:
                                startActivity(new Intent(getCtx(), MenuA.class));
                                return;
                            case 7:
                                return;
                            default:
                                return;
                        }
                    }
                });
    }

    public void showDataReadWrite(){
        presenter.showDialog(getResources().getString(R.string.android_data_read_write),
                getResources().getStringArray(R.array.android_data_read_write2),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                return;
                            case 1:
                                return;
                            case 2:
                                return;
                            case 3:
                                return;
                            case 4:
                                return;
                            default:
                                return;
                        }
                    }
                });
    }

    public void showMediaPractice(){
        presenter.showDialog(getResources().getString(R.string.android_media_practice),
                getResources().getStringArray(R.array.android_media_practice2),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                startActivity(new Intent(getCtx(), AudioSoundsA.class));
                                return;
                            case 1:
                                return;
                            default:
                                return;
                        }
                    }
                });
    }

    public void showSDKExplore(){
        presenter.showDialog(getResources().getString(R.string.android_sdk_explore),
                getResources().getStringArray(R.array.android_sdk_explore2),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                return;
                            case 1:
                                return;
                            case 2:
                                return;
                            case 3:
                                return;
                            case 4:
                                return;
                            default:
                                return;
                        }
                    }
                });
    }

    String creation_model = "Singleton、Abstract Factory、\n Factory Method、Builder、Prototype";
    String action_model = "Iterator、Observer、Template Method、\n Command、State、Strategy、\n Chain of Responsibility、 Mediator、\n Visitor、Interpreter、Memento";
    String structure_model = "Composite、Facade(外观模式)、\n Proxy、Adapter、Decorator、\n Bridge、 Flyweight(享元模式)";
    String design_principle = "1.开闭原则（Open Close Principle\n2.里氏代换原则（Liskov Substitution Principle）\n3.依赖倒转原则（Dependence Inversion Principle）" +
            "\n4.接口隔离原则（Interface Segregation Principle）\n5.迪米特法则（Demeter Principle）\n6.合成复用原则（Composite Reuse Principle）";
    public void showDesignModel() {
        presenter.showDialog(getResources().getString(R.string.design_model),
                getResources().getStringArray(R.array.design_model2),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                DialogUtil.showTitleAndContent(getCtx(),getCtx().getResources().getString(R.string.design_model_principle),design_principle);
                                return;
                            case 1:
                                DialogUtil.showTitleAndContent(getCtx(),"创造型模式",creation_model);
                                return;
                            case 2:
                                DialogUtil.showTitleAndContent(getCtx(),"行为型模式",action_model);
                                return;
                            case 3:
                                DialogUtil.showTitleAndContent(getCtx(),"结构型模式",structure_model);
                                return;
                            default:
                                return;
                        }
                    }
                });
    }

}

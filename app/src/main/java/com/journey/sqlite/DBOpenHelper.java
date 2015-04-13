package com.journey.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.journey.data.M1010Constant;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_NEWS_CONFIG = "create table " + DBConst.TABLE_NEWS_CONFIG + " (" + DBConst.KEY_ID
            + " integer primary key AUTOINCREMENT, " + DBConst.KEY_NEWS_ID_COLUMN + " text not null UNIQUE, "
            + DBConst.KEY_NEWS_NAME_COLUMN + " text not null, " + DBConst.KEY_NEWS_ISCHECKED_COLUMN
            + " integer, " + DBConst.KEY_NEWS_POSITION_COLUMN + " integer); ";

    private static final String CREATE_TABLE_NEWS_KEEP = "create table " + DBConst.TABLE_NEWS_KEEPED
            + " ("
            + DBConst.KEY_ID + " integer primary key AUTOINCREMENT, "
            + M1010Constant.MODEL_NEWS_ID + " text not null UNIQUE, "
            + M1010Constant.MODEL_NEWS_TITLE + " text not null, "
            + M1010Constant.MODEL_NEWS_TIME+ " text, "
            + M1010Constant.MODEL_NEWS_COLUMN+ " text, "
            + M1010Constant.MODEL_NEWS_IMAGE + " text); ";

    private static final String INIT_TABLE_NEWS_CONFIG = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_YAO_WEN_COLUMN  +"','"+ M1010Constant.NEWS_YAO_WEN_NAME +"',"+ "'1'" + ","+ "'1'" +"),"
            + "('" + M1010Constant.NEWS_ZHI_BO_COLUMN  +"','"+ M1010Constant.NEWS_ZHI_BO_NAME +"',"+ "'1'" + ","+ "'2'" +"),"
            + "('" + M1010Constant.NEWS_WAI_HUI_COLUMN  +"','"+ M1010Constant.NEWS_WAI_HUI_NAME +"',"+ "'1'" + ","+ "'3'" +"),"
            + "('" + M1010Constant.NEWS_JIN_YIN_COLUMN  +"','"+ M1010Constant.NEWS_JIN_YIN_NAME +"',"+ "'1'" + ","+ "'4'" +"),"
            + "('" + M1010Constant.NEWS_SHI_YOU_COLUMN  +"','"+ M1010Constant.NEWS_SHI_YOU_NAME +"',"+ "'1'" + ","+ "'5'" +"),"
            + "('" + M1010Constant.NEWS_SHANG_PIN_COLUMN  +"','"+ M1010Constant.NEWS_SHANG_PIN_NAME +"',"+ "'1'" + ","+ "'6'" +"),"
            + "('" + M1010Constant.NEWS_GU_SHI_COLUMN  +"','"+ M1010Constant.NEWS_GU_SHI_NAME +"',"+ "'1'" + ","+ "'7'" +"),"
            + "('" + M1010Constant.NEWS_ZHAI_SHI_COLUMN  +"','"+ M1010Constant.NEWS_ZHAI_SHI_NAME +"',"+ "'1'" + ","+ "'8'" +"),"
            + "('" + M1010Constant.NEWS_MEI_LIAN_CHU_COLUMN  +"','"+ M1010Constant.NEWS_MEI_LIAN_CHU_NAME +"',"+ "'1'" + ","+ "'9'" +"),"
            + "('" + M1010Constant.NEWS_YANG_HANG_COLUMN  +"','"+ M1010Constant.NEWS_YANG_HANG_NAME +"',"+ "'1'" + ","+ "'10'" +"),"
            + "('" + M1010Constant.NEWS_MEI_GUO_COLUMN  +"','"+ M1010Constant.NEWS_MEI_GUO_NAME +"',"+ "'1'" + ","+ "'11'" +"),"
            + "('" + M1010Constant.NEWS_ZHONG_GUO_COLUMN  +"','"+ M1010Constant.NEWS_ZHONG_GUO_NAME +"',"+ "'1'" + ","+ "'12'" +"),"
            + "('" + M1010Constant.NEWS_OU_ZHOU_COLUMN  +"','"+ M1010Constant.NEWS_OU_ZHOU_NAME +"',"+ "'1'" + ","+ "'13'" +"),"
            + "('" + M1010Constant.NEWS_MEI_ZHOU_COLUMN  +"','"+ M1010Constant.NEWS_MEI_ZHOU_NAME +"',"+ "'1'" + ","+ "'14'" +"),"
            + "('" + M1010Constant.NEWS_YA_ZHOU_COLUMN  +"','"+ M1010Constant.NEWS_YA_ZHOU_NAME +"',"+ "'1'" + ","+ "'15'" +"),"
            + "('" + M1010Constant.NEWS_KEEPED_COLUMN  +"','"+ M1010Constant.NEWS_KEEPED_NAME +"',"+ "'1'" + ","+ "'16'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_YAO_WEN_COLUMN = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_YAO_WEN_COLUMN  +"','"+ M1010Constant.NEWS_YAO_WEN_NAME +"',"+ "'1'" + ","+ "'1'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_ZHI_BO_COLUMN = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_ZHI_BO_COLUMN  +"','"+ M1010Constant.NEWS_ZHI_BO_NAME +"',"+ "'1'" + ","+ "'2'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_WAI_HUI_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_WAI_HUI_COLUMN  +"','"+ M1010Constant.NEWS_WAI_HUI_NAME +"',"+ "'1'" + ","+ "'3'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_JIN_YIN_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_JIN_YIN_COLUMN  +"','"+ M1010Constant.NEWS_JIN_YIN_NAME +"',"+ "'1'" + ","+ "'4'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_SHI_YOU_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_SHI_YOU_COLUMN  +"','"+ M1010Constant.NEWS_SHI_YOU_NAME +"',"+ "'1'" + ","+ "'5'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_SHANG_PIN_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_SHANG_PIN_COLUMN  +"','"+ M1010Constant.NEWS_SHANG_PIN_NAME +"',"+ "'1'" + ","+ "'6'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_GU_SHI_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_GU_SHI_COLUMN  +"','"+ M1010Constant.NEWS_GU_SHI_NAME +"',"+ "'1'" + ","+ "'7'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_ZHAI_SHI_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_ZHAI_SHI_COLUMN  +"','"+ M1010Constant.NEWS_ZHAI_SHI_NAME +"',"+ "'1'" + ","+ "'8'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_MEI_LIAN_CHU_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_MEI_LIAN_CHU_COLUMN  +"','"+ M1010Constant.NEWS_MEI_LIAN_CHU_NAME +"',"+ "'1'" + ","+ "'9'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_YANG_HANG_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_YANG_HANG_COLUMN  +"','"+ M1010Constant.NEWS_YANG_HANG_NAME +"',"+ "'1'" + ","+ "'10'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_MEI_GUO_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_MEI_GUO_COLUMN +"','"+ M1010Constant.NEWS_MEI_GUO_NAME +"',"+ "'1'" + ","+ "'11'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_ZHONG_GUO_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_ZHONG_GUO_COLUMN +"','"+ M1010Constant.NEWS_ZHONG_GUO_NAME +"',"+ "'1'" + ","+ "'12'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_OU_ZHOU_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_OU_ZHOU_COLUMN  +"','"+ M1010Constant.NEWS_OU_ZHOU_NAME +"',"+ "'1'" + ","+ "'13'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_MEI_ZHOU_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_MEI_ZHOU_COLUMN  +"','"+ M1010Constant.NEWS_MEI_ZHOU_NAME +"',"+ "'1'" + ","+ "'14'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_YA_ZHOU_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_YA_ZHOU_COLUMN +"','"+ M1010Constant.NEWS_YA_ZHOU_NAME +"',"+ "'1'" + ","+ "'15'" +");";

    private static final String INIT_TABLE_NEWS_CONFIG_NEWS_KEEPED_NAME = "INSERT INTO " + DBConst.TABLE_NEWS_CONFIG
            + " ("
            + DBConst.KEY_NEWS_ID_COLUMN + ","
            + DBConst.KEY_NEWS_NAME_COLUMN + ","
            + DBConst.KEY_NEWS_ISCHECKED_COLUMN +","
            + DBConst.KEY_NEWS_POSITION_COLUMN + ")"
            + " VALUES "
            + "('" + M1010Constant.NEWS_KEEPED_COLUMN +"','"+ M1010Constant.NEWS_KEEPED_NAME +"',"+ "'1'" + ","+ "'16'" +");";






    public DBOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, DBConst.DB_NAME, null, DB_VERSION);
    }

    public DBOpenHelper(Context context) {
        super(context, DBConst.DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NEWS_CONFIG);
        db.execSQL(CREATE_TABLE_NEWS_KEEP);
        try{
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1){
                db.execSQL(INIT_TABLE_NEWS_CONFIG);
            }else{
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_YAO_WEN_COLUMN);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_ZHI_BO_COLUMN);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_WAI_HUI_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_JIN_YIN_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_SHI_YOU_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_SHANG_PIN_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_GU_SHI_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_ZHAI_SHI_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_MEI_LIAN_CHU_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_YANG_HANG_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_MEI_GUO_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_ZHONG_GUO_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_OU_ZHOU_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_MEI_ZHOU_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_YA_ZHOU_NAME);
                db.execSQL(INIT_TABLE_NEWS_CONFIG_NEWS_KEEPED_NAME);

            }

        }catch (Exception e){
            e.printStackTrace();
            //MyApplication.htlog.i("onCreate Exception");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF IT EXISTS " + DBConst.TABLE_NEWS_CONFIG);
        db.execSQL("DROP TABLE IF IT EXISTS " + DBConst.TABLE_NEWS_KEEPED);
        onCreate(db);
    }

}

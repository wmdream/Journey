package com.journey.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.journey.data.M1010Constant;
import com.journey.data.NewsConfig;
import com.journey.data.NewsModel;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBOpenHelper(context);
        openDB();
        // log.i("DBManager");
    }

    public void add(List<NewsConfig> list) {
        // 开始事务
        db.beginTransaction();

        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                NewsConfig news = list.get(i);
                //MyApplication.htlog.i("NewsConfig " + i + "->" + news.toString());
                cv.put(DBConst.KEY_NEWS_ID_COLUMN, news.getNews_type());
                cv.put(DBConst.KEY_NEWS_NAME_COLUMN, news.getNews_name());
                cv.put(DBConst.KEY_NEWS_ISCHECKED_COLUMN, news.isChecked());
                cv.put(DBConst.KEY_NEWS_POSITION_COLUMN, news.getPosition());
                db.insert(DBConst.TABLE_NEWS_CONFIG, null, cv);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            //MyApplication.htlog.i("数据存储异常");
        } finally {
            db.endTransaction();
        }
    }

    /*public boolean exits(String table){
        boolean exits = false;
        String sql = "select * from sqlite_master where name="+"'"+table+"'";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount()!=0){
            exits = true;
        }
        return exits;
    }*/

    public void add(NewsConfig news) {
        // 开始事务
        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(DBConst.KEY_NEWS_ID_COLUMN, news.getNews_type());
            cv.put(DBConst.KEY_NEWS_NAME_COLUMN, news.getNews_name());
            cv.put(DBConst.KEY_NEWS_ISCHECKED_COLUMN, news.isChecked());
            cv.put(DBConst.KEY_NEWS_POSITION_COLUMN, news.getPosition());
            db.insert(DBConst.TABLE_NEWS_CONFIG, null, cv);
            db.setTransactionSuccessful();// 设置事务成功完成
        } catch (Exception e) {
            // log.i("数据存储异常");
        } finally {
            db.endTransaction();
        }

    }

    public ArrayList<NewsConfig> queryAll() {
        NewsConfig item;
        ArrayList<NewsConfig> res = new ArrayList<NewsConfig>();
        Cursor c = queryCursorByall();
        if (c != null) {
            while (c.moveToNext()) {
                item = new NewsConfig();
                item.setNews_type(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_ID_COLUMN)));
                item.setId((c.getInt(c.getColumnIndex(DBConst.KEY_ID))));
                item.setNews_name(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_NAME_COLUMN)));
                item.setPosition(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_POSITION_COLUMN)));
                item.setChecked(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_ISCHECKED_COLUMN)) > 0);
                res.add(item);

            }
            c.close();
        }
        return res;
    }

    public NewsConfig queryFrist() {
        NewsConfig item = new NewsConfig();
        Cursor c = queryCursorByall();
        if (c != null) {
            while (c.moveToNext()) {
                item.setNews_type(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_ID_COLUMN)));
                item.setId((c.getInt(c.getColumnIndex(DBConst.KEY_ID))));
                item.setNews_name(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_NAME_COLUMN)));
                item.setPosition(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_POSITION_COLUMN)));
                item.setChecked(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_ISCHECKED_COLUMN)) > 0);
                break;
            }
            c.close();
        }
        return item;
    }

    public ArrayList<NewsConfig> queryChecked() {

        ArrayList<NewsConfig> res = new ArrayList<NewsConfig>();
        Cursor c = queryCursorByChecked();
        if (c != null) {
            while (c.moveToNext()) {
                NewsConfig item = new NewsConfig();
                item.setNews_type(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_ID_COLUMN)));
                item.setId((c.getInt(c.getColumnIndex(DBConst.KEY_ID))));
                item.setNews_name(c.getString(c.getColumnIndex(DBConst.KEY_NEWS_NAME_COLUMN)));
                item.setPosition(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_POSITION_COLUMN)));
                item.setChecked(c.getInt(c.getColumnIndex(DBConst.KEY_NEWS_ISCHECKED_COLUMN)) > 0);
                res.add(item);

            }
            c.close();
        }
        return res;
    }

    public Cursor queryCursorByall() {
        if (db == null) {
            return null;
        }
        Cursor c = db.rawQuery("select * from " + DBConst.TABLE_NEWS_CONFIG + " order by _position ; ", null);
        if (c == null) {
            // log.i("queryAllCursor return null");
        }
        return c;
    }

    public Cursor queryCursorByChecked() {
        if (db == null) {
            return null;
        }
        Cursor c = db.rawQuery("select * from " + DBConst.TABLE_NEWS_CONFIG + " where _ischecked = 1 order by _position; ", null);
        if (c == null) {
            // log.i("queryAllCursor return null");
        }
        return c;
    }

    public int update(List<NewsConfig> list) {
        int res = -1;
        // 开始事务
        db.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                NewsConfig news = list.get(i);
                String where = DBConst.KEY_NEWS_ID_COLUMN + "=?";
                String[] whereValue = {news.getNews_type()};
                cv.put(DBConst.KEY_NEWS_ID_COLUMN, news.getNews_type());
                cv.put(DBConst.KEY_NEWS_NAME_COLUMN, news.getNews_name());
                cv.put(DBConst.KEY_NEWS_ISCHECKED_COLUMN, news.isChecked());
                //  cv.put(DBConst.KEY_NEWS_POSITION_COLUMN, news.getPosition());
                cv.put(DBConst.KEY_NEWS_POSITION_COLUMN, i);
                //  res =  db.update(DBConst.DB_TABLE, cv,null,null);
                res = db.update(DBConst.TABLE_NEWS_CONFIG, cv, where, whereValue);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // log.i("数据存储异常");
        } finally {
            db.endTransaction();
        }
        return res;
    }

//    public int update(String code, AlertPrice alertpriceitem) {
//        int res = -1;
//        String where = DBConst.KEY_COLUMN_PA_CODE + "=?";
//        String[] whereValue = { code };
//        ContentValues cv = new ContentValues();
//        cv.put(DBConst.KEY_COLUMN_PA_KEY, alertpriceitem.getKey());
//        cv.put(DBConst.KEY_COLUMN_PA_CODE, alertpriceitem.getCode());
//        cv.put(DBConst.KEY_COLUMN_PA_NAME, alertpriceitem.getName());
//        cv.put(DBConst.KEY_COLUMN_PA_DF, alertpriceitem.getDf());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTABOVE, alertpriceitem.getAlertabove());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTABOVE_STATE, alertpriceitem.isAlertabove_state());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTBELOW, alertpriceitem.getAlertbelow());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTBELOW_STATE, alertpriceitem.isAlertbelow_state());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTCHANGE100, alertpriceitem.getAlertchange100());
//        cv.put(DBConst.KEY_COLUMN_PA_ALERTCHANGE100_STATE, alertpriceitem.isAlertchange100_state());
//        res = db.update(DBConst.DB_TABLE_PA, cv, where, whereValue);
//        return res;
//    }

    public void closeDB() {
        try {
            db.close();
        } catch (Exception e) {
            // log.i("db close exception");
        }

    }

    public synchronized void openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (Exception e) {
            //MyApplication.htlog.i("openDB Exception");
        }

    }


    /**
     * 收藏新闻
     */
    public void addkeepednews(NewsModel news) {
        //MyApplication.htlog.i("开始事务");
        // 开始事务
        db.beginTransaction();
        // MyApplication.htlog.i("try");
        try {
            ContentValues cv = new ContentValues();
            cv.put(M1010Constant.MODEL_NEWS_ID, news.getNews_id());
            //MyApplication.htlog.i("addkeepednews-id-" + news.getNews_id());
            cv.put(M1010Constant.MODEL_NEWS_TITLE, news.getNews_title());
            cv.put(M1010Constant.MODEL_NEWS_TIME, news.getNews_time());
            cv.put(M1010Constant.MODEL_NEWS_IMAGE, news.getNews_image());
            cv.put(M1010Constant.MODEL_NEWS_COLUMN, news.getNews_column());
            db.insert(DBConst.TABLE_NEWS_KEEPED, null, cv);
            db.setTransactionSuccessful();// 设置事务成功完成
        } catch (Exception e) {
            // log.i("数据存储异常");
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public void deleteKeeppedItem(String newsid) {
        db.execSQL("delete from newskeeped where NewsID = " + newsid);
    }


    public ArrayList<NewsModel> queryKeepedNews() {
        NewsModel item;
        ArrayList<NewsModel> res = new ArrayList<NewsModel>();
        Cursor c = queryAllCursorFromKeeped();
        if (c != null) {
            while (c.moveToNext()) {
                item = new NewsModel();
                item.setNews_id(c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_ID)));
                item.setNews_title(c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_TITLE)));
                item.setNews_time(c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_TIME)));
                item.setNews_image(c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_IMAGE)));
                item.setNews_column(c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_COLUMN)));
                //MyApplication.htlog.i("item->" + item);
                res.add(item);
            }
            c.close();
        }
        return res;
    }

    public ArrayList<String> queryKeepedNewsid() {
        String item;
        ArrayList<String> res = new ArrayList<String>();
        Cursor c = queryCursorByNewsidFromKeeped();
        if (c != null) {
            while (c.moveToNext()) {
                item = c.getString(c.getColumnIndex(M1010Constant.MODEL_NEWS_ID));
                res.add(item);
            }
            c.close();
        }
        return res;
    }

    public Cursor queryCursorByNewsidFromKeeped() {
        if (db == null) {
            return null;
        }
        Cursor c = db.rawQuery("select NewsID from " + DBConst.TABLE_NEWS_KEEPED + " ; ", null);
        if (c == null) {
            // log.i("queryAllCursor return null");
        }
        return c;
    }

    public Cursor queryAllCursorFromKeeped() {
        if (db == null) {
            return null;
        }
        Cursor c = db.rawQuery("select * from " + DBConst.TABLE_NEWS_KEEPED + " order by _id desc; ", null);
        if (c == null) {
            // log.i("queryAllCursor return null");
        }
        return c;
    }
}

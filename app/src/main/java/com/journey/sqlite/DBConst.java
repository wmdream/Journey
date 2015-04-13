package com.journey.sqlite;

public class DBConst {
	// ///////////////////////////////
	// //新闻配置SQLite 数据库相关
	// ///////////////////////////////
	public static final String DB_NAME = "newsdb2.db";
	public static final String TABLE_NEWS_CONFIG = "newsconfig";
    public static final String TABLE_NEWS_KEEPED = "newskeeped";

	public static final String KEY_ID = "_id";

	public static final String KEY_NEWS_ID_COLUMN = "_news_type";
	public static final String KEY_NEWS_NAME_COLUMN = "_name";
	public static final String KEY_NEWS_ISCHECKED_COLUMN = "_ischecked"; //0:未选中，1：选中
	public static final String KEY_NEWS_POSITION_COLUMN = "_position";

   // public static final String KEY_COLUMN_NEWS_ID = "_news_id";

}

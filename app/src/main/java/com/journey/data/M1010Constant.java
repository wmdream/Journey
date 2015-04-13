package com.journey.data;
public class M1010Constant {

    public static final String NEWS_URL = "http://htmdatanet.fx678.com";//http://htmdatanet.fx678.com/ ;   http://192.168.0.68/
    //public static final String NEWS_URL = "http://192.168.0.68";//http://htmdatanet.fx678.com/ ;   http://192.168.0.68/
    /**
     * column
     * last_id
     * time
     * key	md5(column + last_id + time_key + md5_key)
     */
    public static final String URL_NEWS_LIST = NEWS_URL + "/news/news.aspx";
    /**
     * news_id
     * time
     * key	md5(news_id + time_key + md5_key)
     */
    public static final String URL_NEWS_DETAIL = NEWS_URL + "/news/newsDetail.aspx";
    public static final String URL_NEWS_SHARE = "http://htmdata.fx678.com/news/share/news.php";

    public static final String NEWS_COLUMN = "news_column";
    public static final String NEWS_ID = "news_id";
    public static final String MD5KEY = "htm_key_data_net_2099";
    public static final String MD5_SHARE_KEY = "htm_key_news_2099";

    public static final String NEWS_YAO_WEN_COLUMN = "NEWS_YAO_WEN";
    public static final String NEWS_ZHI_BO_COLUMN = "NEWS_ZHI_BO";
    public static final String NEWS_WAI_HUI_COLUMN = "NEWS_WAI_HUI";
    public static final String NEWS_JIN_YIN_COLUMN = "NEWS_JIN_YIN";
    public static final String NEWS_SHI_YOU_COLUMN = "NEWS_XI_DU_SHI_YOU";
    public static final String NEWS_SHANG_PIN_COLUMN = "NEWS_SHANG_PIN";
    public static final String NEWS_GU_SHI_COLUMN = "NEWS_GU_SHI";
    public static final String NEWS_ZHAI_SHI_COLUMN = "NEWS_ZHAI_SHI";
    public static final String NEWS_MEI_LIAN_CHU_COLUMN = "NEWS_MEI_LIAN_CHU";
    public static final String NEWS_YANG_HANG_COLUMN = "NEWS_YANG_HANG";
    public static final String NEWS_MEI_GUO_COLUMN = "NEWS_MEI_GUO";
    public static final String NEWS_ZHONG_GUO_COLUMN = "NEWS_ZHONG_GUO";
    public static final String NEWS_OU_ZHOU_COLUMN = "NEWS_OU_ZHOU";
    public static final String NEWS_MEI_ZHOU_COLUMN = "NEWS_MEI_ZHOU";
    public static final String NEWS_YA_ZHOU_COLUMN = "NEWS_YA_ZHOU";//
    public static final String NEWS_KUAI_XUN_COLUMN = "NEWS_KUAI_XUN";
    public static final String NEWS_REMOTE_PUSH_COLUMN = "NEWS_REMOTE_PUSH";
    public static final String NEWS_TOU_TIAO_COLUMN = "NEWS_TOU_TIAO";
    //  public static final String NEWS_TOU_TIAO_COLUMN = "NEWS_TOU_TIAO";
//    public static final String NEWS_TUI_JIAN_COLUMN = "NEWS_TUI_JIAN";

    public static final String NEWS_KEEPED_COLUMN = "NEWS_SHOU_CANG";

    public static final String NEWS_YAO_WEN_NAME = "要闻";
    public static final String NEWS_ZHI_BO_NAME = "直播";
    public static final String NEWS_WAI_HUI_NAME = "外汇";
    public static final String NEWS_JIN_YIN_NAME = "金银";
    public static final String NEWS_SHI_YOU_NAME = "石油";
    public static final String NEWS_SHANG_PIN_NAME = "商品";
    public static final String NEWS_GU_SHI_NAME = "股市";
    public static final String NEWS_ZHAI_SHI_NAME = "债市";
    public static final String NEWS_MEI_LIAN_CHU_NAME = "美联储";
    public static final String NEWS_YANG_HANG_NAME = "央行";
    public static final String NEWS_MEI_GUO_NAME = "美国";
    public static final String NEWS_ZHONG_GUO_NAME = "中国";
    public static final String NEWS_OU_ZHOU_NAME = "欧洲";
    public static final String NEWS_MEI_ZHOU_NAME = "美洲";
    public static final String NEWS_YA_ZHOU_NAME = "亚洲";
    public static final String NEWS_KUAI_XUN_NAME = "快讯";
    //    public static final String NEWS_TOU_TIAO_NAME = "头条";
//    public static final String NEWS_TUI_JIAN_NAME = "推荐";
    public static final String NEWS_KEEPED_NAME = "收藏";


    /**
     * news_model 新闻列表item数据模型
     */
    public static final String MODEL_NEWS_ID = "NewsID";
    public static final String MODEL_NEWS_TITLE = "NewsTitle";
    public static final String MODEL_NEWS_TIME = "NewsTime";
    public static final String MODEL_NEWS_IMAGE = "FirstColImage";
    public static final String MODEL_NEWS_COLUMN = "COLUMN";

    /**
     * 查看新闻后，列表项文字颜色变浅
     */
    public static final String PREFS_READ_NEWS = "prefs_read_news";
    public static final String READ_NEWS_ID = "read_news_id";
}
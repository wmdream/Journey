package com.journey.data;

public class NewsModel {

    private String news_id;
    private String news_title;
    private String news_time; //true==1,false==0
    private String news_image;
    private String news_column;
    private int readstate;

    public int getReadState() {
        return readstate;
    }

    public void setReadState(int readstate) {
        this.readstate = readstate;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public String getNews_time() {
        return news_time;
    }

    public void setNews_time(String news_time) {
        this.news_time = news_time;
    }

    public String getNews_image() {
        return news_image;
    }

    public void setNews_image(String news_image) {
        this.news_image = news_image;
    }

    public String getNews_column() {
        return news_column;
    }

    public void setNews_column(String news_column) {
        this.news_column = news_column;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "news_id='" + news_id + '\'' +
                ", news_title='" + news_title + '\'' +
                ", news_time='" + news_time + '\'' +
                ", news_image='" + news_image + '\'' +
                ", news_column='" + news_column + '\'' +
                ", readstate=" + readstate +
                '}';
    }
}

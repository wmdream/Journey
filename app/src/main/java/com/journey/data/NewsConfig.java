package com.journey.data;

public class NewsConfig {

    public int id;
    public String news_type;
    public String news_name;
    public boolean isChecked; //true==1,false==0
    public int position;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNews_type() {
        return news_type;
    }

    public void setNews_type(String news_type) {
        this.news_type = news_type;
    }

    public String getNews_name() {
        return news_name;
    }

    public void setNews_name(String news_name) {
        this.news_name = news_name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "NewsConfig{" +
                "id=" + id +
                ", news_type='" + news_type + '\'' +
                ", news_name='" + news_name + '\'' +
                ", isChecked=" + isChecked +
                ", position=" + position +
                '}';
    }
}

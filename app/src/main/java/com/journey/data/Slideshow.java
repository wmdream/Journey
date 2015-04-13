package com.journey.data;

import java.util.List;

public class Slideshow {

	private String updatetime; /**updtetime属性代表最后更新时间；检查更新用的*/
	List<SlideFrame> lists;
	
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public List<SlideFrame> getLists() {
		return lists;
	}
	public void setLists(List<SlideFrame> lists) {
		this.lists = lists;
	}

    @Override
    public String toString() {
        return "Slideshow{" +
                "updatetime='" + updatetime + '\'' +
                ", lists=" + lists +
                '}';
    }
}

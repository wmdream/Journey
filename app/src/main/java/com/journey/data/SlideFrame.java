package com.journey.data;

public class SlideFrame {

	private String src;   /**幻灯片的图层*/
	private String bgsrc; /**幻灯片的背景*/
	private String href;  /**点击幻灯片后的动作。如果href为#。无点击效果。*/
	private String target;/**开链接方式，_self  app内嵌浏览器打开 _blank系统浏览器打开*/
	private String title; /**内嵌打开，要在页面顶部显示的标题,app内嵌浏览器打开页面顶部显示titile标题和返回的箭头*/
	
	private String nativeSrc;   /**幻灯片的图层，本地保存的路径*/
	private String nativeBgsrc; /**幻灯片的背景，本地保存的路径*/
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getBgsrc() {
		return bgsrc;
	}
	public void setBgsrc(String bgsrc) {
		this.bgsrc = bgsrc;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNativeSrc() {
		return nativeSrc;
	}
	public void setNativeSrc(String nativeSrc) {
		this.nativeSrc = nativeSrc;
	}
	public String getNativeBgsrc() {
		return nativeBgsrc;
	}
	public void setNativeBgsrc(String nativeBgsrc) {
		this.nativeBgsrc = nativeBgsrc;
	}

    @Override
    public String toString() {
        return "SlideFrame{" +
                "src='" + src + '\'' +
                ", bgsrc='" + bgsrc + '\'' +
                ", href='" + href + '\'' +
                ", target='" + target + '\'' +
                ", title='" + title + '\'' +
                ", nativeSrc='" + nativeSrc + '\'' +
                ", nativeBgsrc='" + nativeBgsrc + '\'' +
                '}';
    }
}

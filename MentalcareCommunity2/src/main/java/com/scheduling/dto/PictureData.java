package com.scheduling.dto;

public class PictureData {

    private String height;
    private String width;
    private String url;
    public PictureData() {
        super();
        // TODO Auto-generated constructor stub
    }
    public PictureData(String height, String width, String url) {
        super();
        this.height = height;
        this.width = width;
        this.url = url;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "PictureData [height=" + height + ", width=" + width + ", url=" + url + "]";
    }
    
}

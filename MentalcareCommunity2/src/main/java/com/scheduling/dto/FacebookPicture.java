package com.scheduling.dto;

public class FacebookPicture {

    private PictureData data;

    public FacebookPicture() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FacebookPicture(PictureData data) {
        super();
        this.data = data;
    }

    public PictureData getData() {
        return data;
    }

    public void setData(PictureData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FacebookPicture [data=" + data + "]";
    }
    
}

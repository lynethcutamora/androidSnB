package com.android.startnboost;

public class RowIdeas {
	private int imageId;
    private String name;
    private String desc;
    private String date;
    private int imagepost;
     
    public RowIdeas(int imageId, String name, String desc, String date,int imagepost) {
        this.imageId = imageId;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.imagepost = imagepost;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getImagepost() {
        return imagepost;
    }
    public void setImagepost(int imagepost) {
        this.imagepost = imagepost;
    }
    @Override
    public String toString() {
        return name + "\n" + desc;
    }   
}

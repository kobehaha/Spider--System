package com.bean;

public class Buyer {//买家评论对对应的买家

    public String picture;

    public String name;


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    @Override
    public String toString() {//重写方法toString()
        return "buyer picture=" + picture + "and name=" + name;

    }


}

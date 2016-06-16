package com.netforce.ray.profile.news;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class NewsData {


    public String imageProPic, imageProduct, content, date;

    NewsData(String imageProPic, String content, String date, String imageProduct) {
        this.imageProPic = imageProPic;
        this.content = content;
        this.date = date;
        this.imageProduct = imageProduct;
    }
}
package com.netforce.ray.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class HomeData {


    public String image, title, price,productid;

    HomeData(String image, String title, String price,String productid) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.productid=productid;


    }


}
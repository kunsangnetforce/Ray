package com.netforce.ray.home;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class RowData {


    public String image, title, price;

    RowData(String image, String title, String price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }
}
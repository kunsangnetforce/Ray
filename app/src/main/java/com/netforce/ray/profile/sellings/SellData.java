package com.netforce.ray.profile.sellings;

import com.netforce.ray.profile.sellings.offer_list.OfferData;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellData {


    public String id;
    public String name;
    public String price;
    public String like;
    public String created;
    public String discount_flag;
    public String product_image;

    public SellData( String id, String name, String price, String like, String created, String discount_flag, String product_image) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.like = like;
        this.created = created;
        this.discount_flag = discount_flag;
        this.product_image = product_image;
    }

    // public ArrayList<OfferData> offerDatas;



}
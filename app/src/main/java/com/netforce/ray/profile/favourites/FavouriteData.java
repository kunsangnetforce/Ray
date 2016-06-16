package com.netforce.ray.profile.favourites;

import com.netforce.ray.profile.favourites.offer_list.OfferData;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FavouriteData {

    public String status;
    public ArrayList<OfferData> offerDatas;

    FavouriteData(ArrayList<OfferData> offerDatas, String status) {
        this.offerDatas = offerDatas;
        this.status=status;
    }
}
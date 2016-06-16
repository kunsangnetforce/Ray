package com.netforce.ray.profile.sellings;

import com.netforce.ray.profile.sellings.offer_list.OfferData;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellData {

    public String status;
    public ArrayList<OfferData> offerDatas;

    SellData(ArrayList<OfferData> offerDatas, String status) {
        this.offerDatas = offerDatas;
        this.status=status;
    }
}
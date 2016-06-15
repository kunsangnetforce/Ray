package com.netforce.ray.profile.sellings;

import com.netforce.ray.profile.sellings.offer_list.InnerRowData;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class RowData {

    public String status;
    public ArrayList<InnerRowData> innerRowDatas;

    RowData(ArrayList<InnerRowData> innerRowDatas,String status) {
        this.innerRowDatas = innerRowDatas;
        this.status=status;
    }
}
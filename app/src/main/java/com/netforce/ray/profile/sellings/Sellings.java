package com.netforce.ray.profile.sellings;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeData;
import com.netforce.ray.profile.sellings.offer_list.OfferData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sellings extends Fragment
{

    private Context context;
    private RecyclerView recyclerView;
    private SellAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<SellData> sellDatas = new ArrayList<>();
     String status;
    String imageurl;
    public Sellings() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sellings, container, false);
        context = getActivity();
        setupRecyclerView(view);





        return view;
    }

    public void callwebservice() {

        String user_selling_list="http://netforce.biz/seeksell/seller/products/user_selling_list?counter=10";
        Ion.with(this)
                .load(user_selling_list)
                .setBodyParameter("user_id", "4")
                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {

                Log.e(" result", result.toString());

                if (result.get("status").getAsString().contains("success"))

                {
                    status="success";

                    JsonArray re = result.getAsJsonArray("data");


                    for (int i = 0; i < re.size(); i++) {

                        JsonObject jsonObject = (JsonObject) re.get(i);
                        JsonObject Product = jsonObject.getAsJsonObject("Product");
                        String name = Product.get("name").getAsString();
                        String price = Product.get("price").getAsString();
                        String product_id = Product.get("id").getAsString();
                        String like = Product.get("like").getAsString();
                        String created_at = Product.get("created").getAsString();
                        String discount_flag = Product.get("discount_flag").getAsString();
                        Log.e("name", name);
                        // homeDatas.add(new HomeData("url", name, price));
                        JsonArray ProductImage = jsonObject.getAsJsonArray("ProductImage");

                        System.out.println("ProductImage ======================" + ProductImage);


                        for (int k = 0; k < ProductImage.size(); k++) {
                            imageurl = "http://netforce.biz/seeksell/files/products/" + ProductImage.get(k).getAsJsonObject().get("name").getAsString();
                            Log.e("imageurl", imageurl);
                        }


                        sellDatas.add(new SellData(product_id, name, price, like, created_at, discount_flag, imageurl));



                        // String image_url="http://netforce.biz/seeksell/files/products/"+ProductImage.get(name);


                    }
                    adapter.notifyDataSetChanged();


//                    Log.e("response webservice", result.toString());
//                    status = result.get("status").getAsString();
//                    if (status.contains("success")) {
//
//                        //JsonObject js=result.getAsJsonObject("data");
//                        JsonElement data =result.get("data");
//                        JsonArray childJSONObject = data.getAsJsonArray();
//
//                        for (int i = 0; i < childJSONObject.size(); i++) {  // **line 2**
//                            JsonElement childJSONObject2 = childJSONObject.get(i);
//                            JsonObject js = (JsonObject) childJSONObject2.getAsJsonObject().get("Product");
//                            Log.e("childJSONObject2", childJSONObject2.toString());
//
//
//                        }
//
//                        Log.e("data", data.toString());
//
//                    } else {
//                        Log.e("status", status);
//
//                    }
                } else {
                    status="unsuccess";
                    Log.e("Excep webservcie1", e.toString());
                }


            }
        });



    }

    private void setupRecyclerView(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        //setupData();
        callwebservice();
        adapter = new SellAdapter(context, sellDatas);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}

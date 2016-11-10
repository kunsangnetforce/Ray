package com.netforce.ray.home.product_detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.PagerAdapter;

import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;

import java.util.ArrayList;

/**
 * Created by abcd on 10/27/2016.
 */
public class ViewPagerAdapter_product_detail extends PagerAdapter {

    private Context mContext;
    ArrayList<String> imageurl;



    public ViewPagerAdapter_product_detail(ProductDetailsActivity mContext, ArrayList<String> productImage_url) {
       this. imageurl=productImage_url;
        this.mContext=mContext;

    }


    public int getCount() {
        return imageurl.size();
    }


    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        Ion.with(imageView)
                //.placeholder(R.drawable.placeholder_image)

                .load(imageurl.get(position));
        //imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }


    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
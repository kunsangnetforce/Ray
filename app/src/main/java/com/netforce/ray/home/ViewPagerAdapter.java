package com.netforce.ray.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.PagerAdapter;
import com.netforce.ray.R;


/**
 * Created by John on 9/15/2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mResources;

    public ViewPagerAdapter(Context mContext, int[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }


    public int getCount() {
        return mResources.length;
    }


    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }


    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

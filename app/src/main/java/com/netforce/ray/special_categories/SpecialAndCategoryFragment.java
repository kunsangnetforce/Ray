package com.netforce.ray.special_categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.netforce.ray.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialAndCategoryFragment extends Fragment {
    ScrollView scrollView;
    int type = 0;

    public SpecialAndCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_special_and_category, container, false);
        Bundle bundle = getArguments();
        type=bundle.getInt("type");
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);
        final TextView textViewSpecial,textViewCategory;
        textViewCategory= (TextView) view.findViewById(R.id.textViewCategory);
        textViewSpecial= (TextView) view.findViewById(R.id.textViewSpecial);
        if (type == 0) {
            scrollView.post(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, textViewSpecial.getTop());
                }
            });
        } else {
            scrollView.post(new Runnable() {
                public void run() {
                    scrollView.scrollTo(0, textViewCategory.getTop());
                }
            });
        }
        return view;
    }

}

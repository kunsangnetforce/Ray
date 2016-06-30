package com.netforce.ray.special_categories;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialAndCategoryFragment extends Fragment {


    public SpecialAndCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_special_and_category, container, false);
    }

}

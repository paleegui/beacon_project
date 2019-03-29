package com.example.projectbeacon.Activities.fragment;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectbeacon.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Location_fragment extends Fragment {


    public Location_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_fragment, container, false);
    }

}

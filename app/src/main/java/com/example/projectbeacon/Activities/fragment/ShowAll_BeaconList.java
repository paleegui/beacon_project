package com.example.projectbeacon.Activities.fragment;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.Database.DatabaseHandler;
import com.example.projectbeacon.R;
import com.example.projectbeacon.UI.RecycleViewAdapter;

import java.util.ArrayList;


public class ShowAll_BeaconList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private RecycleViewAdapter recyeclerViewAdapter;
    private ArrayList<TheBeacon> listItem;
    private ArrayList<TheBeacon> GetFromDatabase;
    private DatabaseHandler db;
    private OnFragmentInteractionListener mListener;

    public ShowAll_BeaconList() {
        // Required empty public constructor
    }

    public static ShowAll_BeaconList newInstance(String param1, String param2) {
        ShowAll_BeaconList fragment = new ShowAll_BeaconList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_all__beacon_list, container, false);
        db = new DatabaseHandler(getContext());

        //UI Showing
        recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerShowAll);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));

//      Get item from database
        listItem = (ArrayList<TheBeacon>) db.getAllbeacons();

        recyeclerViewAdapter = new RecycleViewAdapter(view.getContext(),listItem);
        recyclerView.setAdapter(recyeclerViewAdapter);
        recyeclerViewAdapter.notifyDataSetChanged();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

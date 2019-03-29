package com.example.projectbeacon.UI;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.projectbeacon.Activities.fragment.ShowAll_BeaconList;
import com.example.projectbeacon.Activities.fragment.ShowFloor_BeaconList;
import com.example.projectbeacon.Activities.fragment.ShowRoom_BeaconList;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;


    public PageAdapter(FragmentManager fragmentManager, int NumberOfTabs){
        super(fragmentManager);
        this.mNoOfTabs = NumberOfTabs;

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                ShowAll_BeaconList shawAll_beacon = new ShowAll_BeaconList();
                return shawAll_beacon;
            case 1:
                ShowFloor_BeaconList showFloor_beaconList = new ShowFloor_BeaconList();
                return showFloor_beaconList;
            case 2:
                ShowRoom_BeaconList showRoom_beaconList = new ShowRoom_BeaconList();
                return showRoom_beaconList;

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        switch (position) {
//            case 0:
//                return "อุปกรณ์ทั้งหมด";
//            case 1:
//                return "ชั้น";
//            case 2:
//                return "ห้อง";
//        }
//        return null;
//    }

}

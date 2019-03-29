package com.example.projectbeacon.Activities;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.projectbeacon.Activities.fragment.ListBeacon_fragment;
import com.example.projectbeacon.Activities.fragment.Location_fragment;
import com.example.projectbeacon.R;

public class Main_navigation extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private ListBeacon_fragment listBeacon_fragment;
    private Location_fragment location_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        listBeacon_fragment = new ListBeacon_fragment();
        location_fragment = new Location_fragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_location :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(location_fragment);
                        return true;

                    case R.id.nav_beacon :
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(listBeacon_fragment);
                        return true;

                    case R.id.nav_account :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }

}

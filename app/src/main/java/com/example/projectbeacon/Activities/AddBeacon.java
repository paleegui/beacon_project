package com.example.projectbeacon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.Database.DatabaseHandler;
import com.example.projectbeacon.R;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

public class AddBeacon extends AppCompatActivity {
    private TheBeacon beacon;
    private DatabaseHandler db;
    private String homeName,roomName;
    private int floor;
    private View.OnClickListener view;
    private TagContainerLayout mTagContainerLayout1;
    private TagContainerLayout mTagContainerLayout2;
    private TagContainerLayout mTagContainerLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);

        db = new DatabaseHandler(this);
        Intent intent = getIntent();


        tapClick();
        this.beacon = (TheBeacon) intent.getSerializableExtra("beacon");




    }
    public  void tapClick(){
       // Typeface typeface = Typeface.createFromAsset(, "font/sukhumvitset_text.ttf");
        List<int[]> colors = new ArrayList<int[]>();
        //int[] color = {TagBackgroundColor, TabBorderColor, TagTextColor, TagSelectedBackgroundColor}
        int[] color1 = {Color.WHITE, Color.BLUE, Color.BLACK, Color.YELLOW};
        int[] color2 = {Color.BLUE, Color.BLACK, Color.WHITE, Color.YELLOW};
        colors.add(color2);
        colors.add(color1);

        //tap home name---------------------------------------------------------------------------------------
        mTagContainerLayout1 = (TagContainerLayout) findViewById(R.id.tag_Other_homeName);
        mTagContainerLayout1.setTheme(ColorFactory.NONE);
        mTagContainerLayout1.setTagBackgroundColor(Color.TRANSPARENT);
        //font set
       // mTagContainerLayout1.setTagTypeface(typeface);

        //list default home name
        final List<String> nhome = new ArrayList<>();
        nhome.add("Rattapon's Home"); nhome.add("neay's Home"); nhome.add("AOFFO's Home"); nhome.add("Piyada's Home");

        //set color
        mTagContainerLayout1.setTags(nhome);

        mTagContainerLayout1.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                    homeName = text;
            }

            @Override
            public void onTagLongClick(int position, String text) {
                nhome.remove(position);
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });


        //tap room name-----------------------------------------------------------------------------------------
        mTagContainerLayout2 = (TagContainerLayout) findViewById(R.id.tag_Other_roomName);
        mTagContainerLayout2.setTheme(ColorFactory.NONE);
        mTagContainerLayout2.setTagBackgroundColor(Color.TRANSPARENT);
        //set font
       // mTagContainerLayout2.setTagTypeface(typeface);
        //list default home name
        final List<String> nRoom = new ArrayList<>();
        nRoom.add("ห้องน้ำ"); nRoom.add("ห้องนอน"); nRoom.add("ห้องนั่งเล่น"); nRoom.add("ห้องทำงาน"); nRoom.add("ห้องครัว");

        //set color
        mTagContainerLayout2.setTags(nRoom);
        mTagContainerLayout2.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                roomName = text;
            }

            @Override
            public void onTagLongClick(int position, String text) {
                nRoom.remove(position);
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });

        //tap Floor Number-----------------------------------------------------------------------------------------
        mTagContainerLayout3 = (TagContainerLayout) findViewById(R.id.tag_Other_Floor);
        mTagContainerLayout3.setTheme(ColorFactory.NONE);
        mTagContainerLayout3.setTagBackgroundColor(Color.TRANSPARENT);
        //set font
       // mTagContainerLayout3.setTagTypeface(typeface);
        //list default home name
        final List<String> nFloor = new ArrayList<>();
        nFloor.add("1"); nFloor.add("2"); nFloor.add("3"); nFloor.add("4"); nFloor.add("5");

        //set color
        mTagContainerLayout3.setTags(nFloor);
        mTagContainerLayout3.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                floor = Integer.parseInt(text);
            }

            @Override
            public void onTagLongClick(int position, String text) {
                nFloor.remove(position);
            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });




    }

    public void addBeacon(){
        EditText nameEdit = (EditText)findViewById(R.id.edit_name_addBeacon);
        beacon.setBeaconName(nameEdit.getText().toString());

        db.addBeacon(beacon);
    }

    public void onClick(){
        TextView addButtonHomeName = (TextView)findViewById(R.id.add_other_HomeName);
        TextView addButtonRoomName = (TextView)findViewById(R.id.add_other_Room);
        TextView addButtonFloor = (TextView)findViewById(R.id.add_other_floor);

        EditText newHomeName = (EditText)findViewById(R.id.edit_Other_homeName);
        EditText newRoomName = (EditText)findViewById(R.id.edit_Other_roomName);
        EditText newFloor = (EditText)findViewById(R.id.edit_Other_FloorName);


        addButtonRoomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        addButtonFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

package com.example.projectbeacon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectbeacon.Activities.fragment.ListBeacon_fragment;
import com.example.projectbeacon.Activities.fragment.ShowAll_BeaconList;
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
    private String homeName,roomName,floor;
    private TagContainerLayout mTagContainerLayout1;
    private TagContainerLayout mTagContainerLayout2;
    private TagContainerLayout mTagContainerLayout3;

    private List<String> nFloor;
    private List<String> nhome;
    private List<String> nRoom;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);

        Intent intent = getIntent();
        this.beacon = (TheBeacon) intent.getSerializableExtra("beacon");

        flag = (String)intent.getExtras().get("Flag");
        if(flag.equals("edit")){
            TextView header = (TextView)findViewById(R.id.header_addbeacon);
            header.setText("แก้ไขบีคอน");
        }else if(flag.equals("addBeacon")){
            TextView header = (TextView)findViewById(R.id.header_addbeacon);
            header.setText("ตั้งค่าบีคอน");
        }

        db = new DatabaseHandler(this);
        onClick();
        tapClick();




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
        nhome = new ArrayList<>();
        nhome.add("Rattapon's Home"); nhome.add("neay's Home"); nhome.add("AOFFO's Home"); nhome.add("Piyada's Home");

        //set color
        mTagContainerLayout1.setTags(nhome);

        mTagContainerLayout1.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                    homeName = text;
                    Log.d("ontap", "onTagClick: "+homeName);
                    Log.d("ontap", "position: "+position);
            }

            @Override
            public void onTagLongClick(int position, String text) {
                String wordDelete = nhome.get(position);
                nhome.remove(position);
                mTagContainerLayout1.setTags(nhome);
                Toast.makeText(getApplicationContext(), wordDelete +" ถูกลบแล้ว", Toast.LENGTH_SHORT).show();
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
        nRoom = new ArrayList<>();
        nRoom.add("ห้องน้ำ"); nRoom.add("ห้องนอน"); nRoom.add("ห้องนั่งเล่น"); nRoom.add("ห้องทำงาน"); nRoom.add("ห้องครัว");

        //set color
        mTagContainerLayout2.setTags(nRoom);
        mTagContainerLayout2.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                roomName = text;
                Log.d("ontap", "onTagClick: "+roomName);
                Log.d("ontap", "position: "+position);
            }

            @Override
            public void onTagLongClick(int position, String text) {
                String wordDelete = nRoom.get(position);
                nRoom.remove(position);
                mTagContainerLayout2.setTags(nRoom);
                Toast.makeText(getApplicationContext(), wordDelete +" ถูกลบแล้ว", Toast.LENGTH_SHORT).show();
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
        nFloor = new ArrayList<>();
        nFloor.add("1"); nFloor.add("2"); nFloor.add("3"); nFloor.add("4"); nFloor.add("5");

        //set color
        mTagContainerLayout3.setTags(nFloor);
        mTagContainerLayout3.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                floor = text;
                Log.d("ontap", "onTagClick: "+floor);
                Log.d("ontap", "position: "+position);
            }

            @Override
            public void onTagLongClick(int position, String text) {
                String wordDelete = nFloor.get(position);
                nFloor.remove(position);
                mTagContainerLayout3.setTags(nFloor);
                Toast.makeText(getApplicationContext(), wordDelete +" ถูกลบแล้ว", Toast.LENGTH_SHORT).show();
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
        Button addBeacon = (Button)findViewById(R.id.addBeaconBtn);
        Button previous = (Button)findViewById(R.id.previousToSelect);
        TextView addButtonHomeName = (TextView)findViewById(R.id.add_other_HomeName);
        TextView addButtonRoomName = (TextView)findViewById(R.id.add_other_Room);
        TextView addButtonFloor = (TextView)findViewById(R.id.add_other_floor);

        final EditText newHomeName = (EditText)findViewById(R.id.edit_Other_homeName);
        final EditText newRoomName = (EditText)findViewById(R.id.edit_Other_roomName);
        final EditText newFloor = (EditText)findViewById(R.id.edit_Other_FloorName);

        if(flag.equals("edit")){
            addBeacon.setText("แก้ไข");
        }else if(flag.equals("addBeacon")){
            TextView header = (TextView)findViewById(R.id.header_addbeacon);
            header.setText("เพิ่มบีคอน");
        }

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddBeacon.this, Select_beacon.class));
            }
        });

        addButtonFloor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nFloor.add(newFloor.getText().toString());
                mTagContainerLayout3.setTags(nFloor);
                newFloor.setText("");
            }
        });

        addButtonRoomName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nRoom.add(newRoomName.getText().toString());
                mTagContainerLayout2.setTags(nRoom);
                newRoomName.setText("");
            }
        });

        addButtonHomeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhome.add(newHomeName.getText().toString());
                mTagContainerLayout1.setTags(nhome);
                newHomeName.setText("");
            }
        });

            addBeacon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag.equals("edit")){
                        //click for edit
                        db.updateBeacon(beacon);
                    }else if(flag.equals("addBeacon")){
                        //click for add Beacon
                        if (homeName != null && roomName != null && floor != null) {
                            beacon.setRoomName(roomName);
                            beacon.setHomeName(homeName);
                            beacon.setFloor(floor);
                            db.addBeacon(beacon);
                            Toast.makeText(getApplicationContext(), "ลงทะเบียนบีคอนแล้ว", Toast.LENGTH_SHORT).show();
                            Log.d("has cliked ", ": ");

                            startActivity(new Intent(AddBeacon.this, Select_beacon.class));

                        } else {
                            Log.d("NULL", "Null in addbeacon ");
                            //แจ้งเตือนหรือแสดงข้อความแจ้งเตือน เมื่อกด add แล้วไม่มีชื่อ หรือ ห้อง หรือ ชั้น
                            String wordException = "คุณยังไม่ได้เลือกหรือใส่";
                            if(beacon == null){
                                wordException = wordException+ "ชื่อบีคอน";
                            }
                            if(homeName == null){
                                wordException = wordException+ " ชื่อบ้าน";
                            }
                            if(roomName == null){
                                wordException = wordException+ " ชื่อห้อง";
                            }
                            if(floor == null){
                                wordException = wordException + " ชั้น";
                            }

                            Toast.makeText(getApplicationContext(), wordException, Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            });
    }


}

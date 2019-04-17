package com.example.projectbeacon.Activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.projectbeacon.Activities.MainActivity;
import com.example.projectbeacon.R;

import java.util.Calendar;

public class User_Register extends AppCompatActivity {

    LinearLayout warpEditText;
    Animation fadeInBottom;
    Animation fadeInTop;
    EditText day_EditText;
    EditText month_EditText;
    EditText year_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__register);

        init();
        anime();
        onClick();

    }

    private void init(){
        fadeInBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fadeInTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        warpEditText = (LinearLayout)findViewById(R.id.wrap_register_edit);
        day_EditText = (EditText)findViewById(R.id.day);
        month_EditText = (EditText)findViewById(R.id.month);
        year_EditText = (EditText)findViewById(R.id.year);

        day_EditText.setFocusable(false);
        month_EditText.setFocusable(false);
        year_EditText.setFocusable(false);
    }
    private void anime(){
        warpEditText.startAnimation(fadeInTop);
    }

    private void onClick(){
        Button registerBtn = (Button)findViewById(R.id.registerBtn_register);
        registerBtn.startAnimation(fadeInBottom);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText)findViewById(R.id.edit_username_register);
                EditText password = (EditText)findViewById(R.id.edit_password_register);
                EditText email = (EditText)findViewById(R.id.edit_email_register);
                EditText phoneNum = (EditText)findViewById(R.id.edit_phoneN_register);
            }
        });

        day_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDateDisplay();
            }
        });

        month_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDateDisplay();
            }
        });

        year_EditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDateDisplay();
            }
        });
    }

    private void birthDateDisplay(){
        DatePickerDialog.OnDateSetListener mDateSetListener;

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("Date", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                day_EditText.setText(""+day);
                month_EditText.setText(""+month);
                year_EditText.setText(""+year);
                // insert to data base ต่อ
            }
        };

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
}

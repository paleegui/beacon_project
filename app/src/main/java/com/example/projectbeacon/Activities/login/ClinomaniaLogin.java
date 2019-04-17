package com.example.projectbeacon.Activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectbeacon.Activities.MainActivity;
import com.example.projectbeacon.R;
import com.google.common.math.Quantiles;

public class ClinomaniaLogin extends AppCompatActivity {

    Animation frombotton;
    Animation fadeButton;
    Animation fade_out_center;
    Button loginBtn;
    Button loginBtn2;
    TextView forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinomania_login);

        init();
        onClick();

    }
    private void init(){
        frombotton = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fadeButton = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fade_out_center = AnimationUtils.loadAnimation(this,R.anim.fade_out_center);
    }

    private void onClick(){
        loginBtn = (Button)findViewById(R.id.login_button);
        loginBtn2 = (Button)findViewById(R.id.login_button2);
        forgetpassword = (TextView)findViewById(R.id.forgetpassWord);
        final TextView register1 = (TextView)findViewById(R.id.register_textview);
        final TextView register2 = (TextView)findViewById(R.id.register_textview2);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //<start> invisible  คำอธิบายต่างๆ ==================================================
                TextView mdd1 = (TextView)findViewById(R.id.Mddgard);
                TextView mdd2 = (TextView)findViewById(R.id.Mddgard_wellcome);
                TextView text1 = (TextView)findViewById(R.id.textView5);
                TextView text2 = (TextView)findViewById(R.id.textView6);

                mdd1.startAnimation(fade_out_center);
                mdd2.startAnimation(fade_out_center);
                text1.startAnimation(fade_out_center);
                text2.startAnimation(fade_out_center);
                mdd1.setVisibility(View.INVISIBLE);
                mdd2.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);

                //<end> invisible  คำอธิบายต่างๆ ====================================================

                //<start> visible username password forget_password ===============================
                EditText userName = (EditText)findViewById(R.id.editText_userName);
                EditText password = (EditText)findViewById(R.id.editText_password);


                userName.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                forgetpassword.setVisibility(View.VISIBLE);
                //<end> visible username password forget_password =================================

                //add animation
                userName.startAnimation(frombotton);
                password.startAnimation(frombotton);
                forgetpassword.startAnimation(frombotton);

                //invisible loginBtn1 and register_TextView
                loginBtn.setVisibility(View.INVISIBLE);
                register1.setVisibility(View.INVISIBLE);


                //Visible btn2 and register_textView2
                loginBtn2.setVisibility(View.VISIBLE);
                register2.setVisibility(View.VISIBLE);
                loginBtn2.startAnimation(fadeButton);
            }
        });



        loginBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClinomaniaLogin.this, User_Register.class));
            }
        });

    }


    private void checkVerify(){

    }
}

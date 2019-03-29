package com.example.projectbeacon.Activities;

import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.projectbeacon.R;

public class ClinomaniaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinomania_login);

        Typeface suk = Typeface.createFromAsset(this.getAssets(), "fonts/sukhumvitset.ttc");


    }
}

package com.example.mylearningapp;

import androidx.appcompat.app.AppCompatActivity;
import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
   TextView tv_quenmatkhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_quenmatkhau = findViewById(R.id.tv_quenmk);
        tv_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuenMK.class);
                startActivity(intent);
            }
        });
    }


    public void slideUp(View view){
        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        customType(MainActivity.this,"bottom-to-up");
    }
}

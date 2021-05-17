package com.example.mylearningapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HocPhan extends AppCompatActivity {
ImageView daicuong;
ImageView chuyennganh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoc_phan2);
        daicuong = findViewById(R.id.daicuong);
        chuyennganh = findViewById(R.id.chuyennganh);
        chuyennganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HocPhan.this, Monhoc.class);
                startActivity(intent);
            }
        });
        daicuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HocPhan.this, Monhoc.class);
                startActivity(intent);
            }
        });
    }
}
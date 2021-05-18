package com.example.mylearningapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WelcomeActivity extends AppCompatActivity {

    Intent in;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        in = getIntent();
        if(in != null) bundle = in.getExtras();
        if(bundle != null){
            int c = bundle.getInt("Frag");
            switch (c){
                case 2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new CaNhanFragment()).commit();
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TKBFragment()).commit();
                    break;
                case 4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new TrangchuFragment()).commit();
                    break;
            }
            Log.d("I:", "if");
        }else{
            Log.d("I:", "else");
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TrangchuFragment()).commit();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_trangchu:
                            selectedFragment = new TrangchuFragment();
                            break;
                        case R.id.nav_tintuc:
                            selectedFragment = new TintucFragment();
                            break;
                        case R.id.nav_canhan:
                            selectedFragment = new CaNhanFragment();
                            break;
                        case  R.id.nav_tkb:
                            selectedFragment = new TKBFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
package com.example.mylearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static maes.tech.intentanim.CustomIntent.customType;


public class MainActivity extends AppCompatActivity {
    TextView tv_quenmatkhau;
    EditText etTK, etMK;
    Button btLogin;
    private JDBCController jdbcController;
    private Connection connection;
    public static ArrayList<Student> list = null;
    private String tk, mk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        event();
    }

    public void init() {
        tv_quenmatkhau = findViewById(R.id.tv_quenmk);
        etTK = findViewById(R.id.etTK);
        etMK = findViewById(R.id.etMK);
        btLogin = findViewById(R.id.btLogin);
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();
        if (list == null || list.size() == 0) list = new ArrayList<>();
    }

    public void event() {
        try {
            if (list.size() == 0) getListTK();
        }catch (Exception e){
            e.printStackTrace();
        }
        tv_quenmatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuenMK.class);
                startActivity(intent);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    tk = etTK.getText().toString().trim();
                    mk = etMK.getText().toString().trim();
                    if (list.size() == 0) getListTK();
                    if (checkLogin()) {
                        slideUp(view);
                    } else {
                        Toast.makeText(MainActivity.this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void slideUp(View view) {
        startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        customType(MainActivity.this, "bottom-to-up");
    }

    public void getListTK() throws Exception {
        Statement statement = null;
        ResultSet rs = null;
        statement = connection.createStatement();
        String sql = "select * from Students";
        rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
        }
        statement.close();
        rs.close();

    }

    public boolean checkLogin() {
        for (Student s : list) {
            if (s.StudentID.trim().equals(tk) && s.Password.trim().equals(mk)) {
                LoginInf.studentID = tk;
                return true;
            }
        }
        return false;
    }


}

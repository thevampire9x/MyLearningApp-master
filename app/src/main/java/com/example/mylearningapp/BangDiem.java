package com.example.mylearningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylearningapp.CustomAdapt.BangDiemAdapter;
import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.Class;
import com.example.mylearningapp.Model.KQHT;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class BangDiem extends AppCompatActivity {
    private JDBCController jdbcController;
    private Connection connection;
    public ArrayList<KQHT> listKQHT = null;
    public Class cls;
    TextView tvHoten, tvMSSV, tvDiem;
    ListView lv;
    Spinner sp1, sp2;
    Button btSubmit;
    String[] arr1, arr2 = null;
    ArrayAdapter<String> adap1, adap2 = null;
    String year, semester;
    BangDiemAdapter adapter = null;
    double diemtb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdiem);
        init();
        event();
    }

    public void init(){
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();
        listKQHT = new ArrayList<>();

        tvHoten = findViewById(R.id.tv_hoten);
        tvMSSV = findViewById(R.id.tv_msv);
        tvDiem = findViewById(R.id.tv_Diem);
        lv = findViewById(R.id.lv_bangdiem);
        sp1 = findViewById(R.id.spinner);
        sp2 = findViewById(R.id.spinner2);
        btSubmit = findViewById(R.id.btnSubmit);
        cls = new Class();
        arr1 = new String[4];
        arr2 = new String[2];
        cls = getUserClass();
        String[] split = cls.Period.split("-");
        int y1 = Integer.parseInt(split[0].trim());
        int y2 = Integer.parseInt(split[1].trim());
        int i = 0;
        while (y1 < y2){
            int tmp = y1+1;
            arr1[i] = y1 + " - " + tmp;
            i++;
            y1 = tmp;
        }
        arr2[0] = "Kỳ 1";
        arr2[1] = "Kỳ 2";
    }

    public void event(){
        adap1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr1);
        sp1.setAdapter(adap1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = arr1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adap2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr2);
        sp2.setAdapter(adap2);
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semester = arr2[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(year.isEmpty() || semester.isEmpty())
                    Toast.makeText(BangDiem.this, "Chưa chọn đủ thông tin!", Toast.LENGTH_SHORT).show();
                else {
                    getListKQHT();
                    if(listKQHT.size() > 0){
                        adapter = new BangDiemAdapter(BangDiem.this, R.layout.item_bangdiem, listKQHT);
                        lv.setAdapter(adapter);
                        int tongTC = 0;
                        double tongTB = 0;
                        for(int i = 0; i < listKQHT.size(); i++){
                            tongTC += listKQHT.get(i).SoTC;
                            tongTB += listKQHT.get(i).DiemTBHP*listKQHT.get(i).SoTC;
                        }
                        diemtb = tongTB/tongTC;
                        tvDiem.setText(diemtb+"");
                    }else {
                        Toast.makeText(BangDiem.this, "Chưa có thông tin bảng điểm!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    public Class getUserClass(){
        Statement statement = null;
        ResultSet rs = null;
        Class c = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from Classes where StudentID = '"+LoginInf.studentID+"'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                c = new Class(rs.getString(0), rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return c;
    }

    public void getListKQHT(){
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from KQHT where StudentID = '"+LoginInf.studentID+"' and NamHoc = '" +year+"' and HocKy = '"+semester+"'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listKQHT.add(new KQHT(rs.getString(0),rs.getString(1),rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),(double) rs.getFloat(8),(double) rs.getFloat(9),(double) rs.getFloat(10),rs.getInt(11)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                rs.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

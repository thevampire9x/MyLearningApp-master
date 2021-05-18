package com.example.mylearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class TrangchuFragment extends Fragment {
    TextView tvUsername, tvUserid;
    CardView userInfo, scheduleInfo, kqhtInfo, ksInfo, subjectInfo, studyPlan;
    private JDBCController jdbcController;
    private Connection connection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);

        init(view);
        event(view);
        // Inflate the layout for this fragment
        return view;

    }

    public void init(View v) {
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();
        tvUsername = v.findViewById(R.id.user_name);
        tvUserid = v.findViewById(R.id.user_id);
        userInfo = v.findViewById(R.id.userInfo);
        scheduleInfo = v.findViewById(R.id.scheduleInfo);
        kqhtInfo = v.findViewById(R.id.kqhtInfo);
        ksInfo = v.findViewById(R.id.ksInfo);
        subjectInfo = v.findViewById(R.id.subjectInfo);
        studyPlan = v.findViewById(R.id.studyPlan);
    }

    public void event(View v) {
        Student s = getStudent();
        tvUsername.setText(s.FullName);
        tvUserid.setText(s.StudentID);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Frag",2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        scheduleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Frag",3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        subjectInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HocPhan.class);
                startActivity(intent);
            }
        });
        kqhtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BangDiem.class);
                startActivity(intent);
            }
        });
        ksInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KhaoSatActivity.class);
                startActivity(intent);
            }
        });
    }

    public Student getStudent() {
        Statement statement = null;
        ResultSet rs = null;
        Student s = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from Students where StudentID ='"+LoginInf.studentID+"'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                s = (new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
        return s;
    }
}
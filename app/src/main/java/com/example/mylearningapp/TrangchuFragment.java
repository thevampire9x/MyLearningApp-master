package com.example.mylearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mylearningapp.Model.Student;


public class TrangchuFragment extends Fragment {
    TextView tvUsername, tvUserid;
    CardView userInfo, scheduleInfo, kqhtInfo, ksInfo, subjectInfo, studyPlan;

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
        Student s = getStudent(LoginInf.studentID);
        tvUsername.setText(s.FullName);
        tvUserid.setText(s.StudentID);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaNhanFragment.class);
                startActivity(intent);
            }
        });
        scheduleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TKBFragment.class);
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

    public Student getStudent(String studentId) {
        Student s = null;
        for (Student tmp : MainActivity.list) {
            if (tmp.StudentID.equals(studentId)) {
                s = tmp;
            }
        }
        return s;
    }
}
package com.example.mylearningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylearningapp.CustomAdapt.TKBAdapter;
import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.Class;
import com.example.mylearningapp.Model.KQHT;
import com.example.mylearningapp.Model.Schedule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TKBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TKBFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TKBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TKBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TKBFragment newInstance(String param1, String param2) {
        TKBFragment fragment = new TKBFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView tvLop, tvNamHoc;
    Button btSubmit;
    Spinner sp1, sp2;
    String[] arr1, arr2;
    ArrayList<String> listWeeks = null;
    private JDBCController jdbcController;
    private Connection connection;
    ListView lv;
    TKBAdapter adapter = null;
    ArrayAdapter<String> adap1, adap2;
    ArrayList<Schedule> listTKB = null;
    String year, semester, week;
    Class cls = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_thoikhoabieu, container, false);
        init(view);
        event(view);
        return view;
    }

    public void init(View v) {
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();

        tvLop = v.findViewById(R.id.tv_lop);
        tvNamHoc = v.findViewById(R.id.tv_namhoc);
        sp1 = v.findViewById(R.id.spinner);
        sp2 = v.findViewById(R.id.spinner2);
        lv = v.findViewById(R.id.lv_tkb);
        btSubmit = v.findViewById(R.id.btnSubmit);
        cls = getUserClass();
        tvLop.setText(cls.ClassID);
        String[] split = cls.Period.split("-");
        int minYear = Integer.parseInt(split[0].trim());
        int curYear = Integer.parseInt(cls.Year);
        year = (minYear + curYear - 1) + " - " + (minYear + curYear);
        arr1 = new String[2];
        arr1[0] = "Kỳ 1";
        arr1[1] = "Kỳ 2";
        getListTKB_weeks();
        arr2 = new String[listWeeks.size()];
        if(listWeeks.size() > 0){
            for(int i = 0; i < listWeeks.size(); i++){
                arr2[i] = listWeeks.get(i);
            }
        }
        semester = "";
        week = "";
    }

    public void event(View v){
        adap1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arr1);
        sp1.setAdapter(adap1);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semester = arr1[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if(listWeeks.size() == 0) Toast.makeText(getContext(), "Chưa có thông tin thời khóa biểu!", Toast.LENGTH_SHORT).show();
        else {
            adap2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arr2);
            sp2.setAdapter(adap2);
            sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    week = arr2[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(semester.equals("") || week.equals("")){
                    Toast.makeText(getContext(), "Chưa chọn đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    getListTKB();
                    adapter = new TKBAdapter(getContext(), R.layout.item_thoikhoabieu, listTKB);
                    lv.setAdapter(adapter);
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

    public void getListTKB_weeks(){
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            String sql = "select distinct Weeks from Schedule where StudentID = '"+LoginInf.studentID+"' and NamHoc = '" +year+"' and HocKy = '"+semester+"' order by Ngay asc";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listWeeks.add(rs.getString(0));
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

    public void getListTKB(){
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from Schedule where StudentID = '"+LoginInf.studentID+"' and NamHoc = '" +year+"' and HocKy = '"+semester+"' and Weeks = '"+week+"'";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listTKB.add(new Schedule(rs.getString(0), rs.getString(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getInt(9),rs.getString(10)));
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
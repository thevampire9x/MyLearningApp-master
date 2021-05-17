package com.example.mylearningapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.mylearningapp.MainActivity.list;


public class CaNhanFragment extends Fragment {
    TextView tvUsername, tvUserid, tvClassId, tvEmail, tvSDT, tvNS, tvQQ;
    private JDBCController jdbcController;
    private Connection connection;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        init(view);
        event(view);
        return view;
    }

    public void init(View v){
        tvUsername = v.findViewById(R.id.tvUserName);
        tvUserid = v.findViewById(R.id.tvUserID);
        tvClassId = v.findViewById(R.id.tvClassID);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvSDT = v.findViewById(R.id.tvSDT);
        tvNS = v.findViewById(R.id.tvNS);
        tvQQ = v.findViewById(R.id.tvQQ);
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();
    }

    public void event(View v){
        Student s = getStudent(LoginInf.studentID);
        tvUsername.setText(s.FullName);
        tvUserid.setText(s.StudentID);
        tvClassId.setText(s.ClassID);
        tvEmail.setText(s.Email);
        tvSDT.setText(s.PhoneNumber);
        tvNS.setText(s.Birthday);
        tvQQ.setText(s.Address);

        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfo("Email");
            }
        });
        tvSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfo("SĐT");
            }
        });
        tvNS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfo("Ngày sinh");
            }
        });
        tvQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeInfo("Địa chỉ");
            }
        });
    }

    public Student getStudent(String studentId) {
        Student s = null;
        for (Student tmp : list) {
            if (tmp.StudentID.equals(studentId)) {
                s = tmp;
            }
        }
        return s;
    }

    public void changeInfo(final String title) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View alertLayout = layoutInflater.inflate(R.layout.item_change_uinf, null);
        TextView tvInfo = alertLayout.findViewById(R.id.tvTitle);
        final EditText etInfo = alertLayout.findViewById(R.id.etChangeInfo);
        Button btConfirm = alertLayout.findViewById(R.id.btnSubmit);

        tvInfo.setText("Nhập thông tin "+title+" mới:");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(alertLayout);
        final AlertDialog dialog = builder.create();
        dialog.show();
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                String info = etInfo.getText().toString().trim();
                if (info.length() < 1) {
                    Toast.makeText(getContext(), "Thông tin nhập vào không hợp lệ!", Toast.LENGTH_SHORT).show();
                } else {
                    if(UpdateInfo(connection, title, info))
                        Toast.makeText(getContext(), "Cập nhật thôn tin thành công!", Toast.LENGTH_SHORT).show();
                    switch (title){
                        case "Email":
                            tvEmail.setText(info);
                            break;
                        case "SĐT":
                            tvSDT.setText(info);
                            break;
                        case "Ngày sinh":
                            tvNS.setText(info);
                            break;
                        case "Địa chỉ":
                            tvQQ.setText(info);
                            break;
                    }
                    getListTK();
                    dialog.cancel();
                }
            }
        });
    }

    public boolean UpdateInfo(Connection connection, String title, String info){
        try {
            Statement statement = connection.createStatement();
            String sql = null;
            switch (title) {
                case "Email":
                    sql = "Update Students set Email = '" + info + "' where StudentID = '" + LoginInf.studentID + "'";
                    break;
                case "SĐT":
                    sql = "Update Students set PhoneNumber = '" + info + "' where StudentID = '" + LoginInf.studentID + "'";
                    break;
                case "Ngày sinh":
                    sql = "Update Students set Birthday = '" + info + "' where StudentID = '" + LoginInf.studentID + "'";
                    break;
                case "Địa chỉ":
                    sql = "Update Students set Address = '" + info + "' where StudentID = '" + LoginInf.studentID + "'";
                    break;
            }
            if (statement.executeUpdate(sql) > 0) {
                statement.close();
                return true;
            } else
                statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void getListTK(){
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from Students";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                list.add(new Student(rs.getString(0), rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
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
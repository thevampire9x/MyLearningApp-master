package com.example.mylearningapp.CustomAdapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mylearningapp.Model.Schedule;
import com.example.mylearningapp.R;

import java.util.List;

public class TKBAdapter extends ArrayAdapter<Schedule> {

    public TKBAdapter(@NonNull Context context, int resource, @NonNull List<Schedule> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_thoikhoabieu, null);
        }
        TextView tvHP, tvTC, tvThu, tvGV, tvSoTiet, tvPhong;

        tvHP = convertView.findViewById(R.id.tv_tenhocohan);
        tvTC = convertView.findViewById(R.id.tv_sotc);
        tvThu = convertView.findViewById(R.id.tv_thu);
        tvGV = convertView.findViewById(R.id.tv_tengvgd);
        tvSoTiet = convertView.findViewById(R.id.tv_sotiet);
        tvPhong = convertView.findViewById(R.id.tv_phong);

        Schedule tkb = getItem(position);

        tvHP.setText(tkb.SubjectName);
        tvTC.setText(tkb.SoTC+"");
        tvThu.setText(tkb.Ngay);
        tvGV.setText(tkb.TeacherName);
        tvSoTiet.setText(tkb.Tiet);
        tvPhong.setText(tkb.Phong);

        return convertView;
    }
}

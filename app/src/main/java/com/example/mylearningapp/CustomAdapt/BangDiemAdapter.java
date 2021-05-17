package com.example.mylearningapp.CustomAdapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mylearningapp.Model.KQHT;
import com.example.mylearningapp.R;

import java.util.List;

public class BangDiemAdapter extends ArrayAdapter<KQHT> {

    public BangDiemAdapter(@NonNull Context context, int resource, @NonNull List<KQHT> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_bangdiem, null);
        }
        TextView tvHP, tvTC, tvDQT, tvDKT, tvDTB;

        tvHP = convertView.findViewById(R.id.tv_tenhocohan);
        tvTC = convertView.findViewById(R.id.tv_sotc);
        tvDQT = convertView.findViewById(R.id.tv_diemquatrinh);
        tvDKT = convertView.findViewById(R.id.tv_diemketthuc);
        tvDTB = convertView.findViewById(R.id.tv_diemtb);

        KQHT bd = getItem(position);

        tvHP.setText(bd.SubjectName);
        tvTC.setText(bd.SoTC +"");
        tvDQT.setText(bd.DiemTP + "");
        tvDKT.setText(bd.DiemKT+"");
        tvDTB.setText(bd.DiemTBHP +"");
        return convertView;
    }
}

package com.example.mylearningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylearningapp.JDBC.JDBCController;
import com.example.mylearningapp.Model.KQHT;
import com.example.mylearningapp.Model.KhaoSat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class KhaoSatActivity extends AppCompatActivity {
    TextView ch1, ch2, ch3, ch4, ch5;
    RadioButton rb11, rb12, rb13, rb14, rb21, rb22, rb23, rb24, rb31, rb32, rb33, rb34, rb41, rb42, rb43, rb44, rb51, rb52, rb53, rb54;
    Button btPre, btNext, btSubmit;
    EditText ct20;
    Spinner sp;
    ArrayList<String> listCH = null;
    int[] listDiemKS = null;
    KhaoSat ks = null;
    ArrayList<KQHT> listKQHT = null;
    private JDBCController jdbcController;
    private Connection connection;
    int page = 0;
    RadioGroup rg1, rg2, rg3, rg4, rg5;
    String subjectID = "";
    String[] arr = null;
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khaosat);
        init();
        event();
    }

    public void init() {
        jdbcController = new JDBCController();
        connection = jdbcController.ConnnectionData();
        sp = findViewById(R.id.sp_hocphan);
        ch1 = findViewById(R.id.tvCauHoi1);
        ch2 = findViewById(R.id.tvCauHoi2);
        ch3 = findViewById(R.id.tvCauHoi3);
        ch4 = findViewById(R.id.tvCauHoi4);
        ch5 = findViewById(R.id.tvCauHoi5);
        rb11 = findViewById(R.id.radioButton_tc11);
        rb12 = findViewById(R.id.radioButton_tc12);
        rb13 = findViewById(R.id.radioButton_tc13);
        rb14 = findViewById(R.id.radioButton_tc14);
        rb21 = findViewById(R.id.radioButton_tc21);
        rb22 = findViewById(R.id.radioButton_tc22);
        rb23 = findViewById(R.id.radioButton_tc23);
        rb24 = findViewById(R.id.radioButton_tc24);
        rb31 = findViewById(R.id.radioButton_tc31);
        rb32 = findViewById(R.id.radioButton_tc32);
        rb33 = findViewById(R.id.radioButton_tc33);
        rb34 = findViewById(R.id.radioButton_tc34);
        rb41 = findViewById(R.id.radioButton_tc41);
        rb42 = findViewById(R.id.radioButton_tc42);
        rb43 = findViewById(R.id.radioButton_tc43);
        rb44 = findViewById(R.id.radioButton_tc44);
        rb51 = findViewById(R.id.radioButton_tc51);
        rb52 = findViewById(R.id.radioButton_tc52);
        rb53 = findViewById(R.id.radioButton_tc53);
        rb54 = findViewById(R.id.radioButton_tc54);
        btNext = findViewById(R.id.btnNext);
        btPre = findViewById(R.id.btnPrevios);
        btSubmit = findViewById(R.id.btnSubmitKS);
        ct20 = findViewById(R.id.etCT20);
        rg1 = findViewById(R.id.radioGroup_diffLevel1);
        rg2 = findViewById(R.id.radioGroup_diffLevel2);
        rg3 = findViewById(R.id.radioGroup_diffLevel3);
        rg4 = findViewById(R.id.radioGroup_diffLevel4);
        rg5 = findViewById(R.id.radioGroup_diffLevel5);
        listKQHT = new ArrayList<>();
        listCH = new ArrayList<>();
        listDiemKS = new int[19];
        listCH.add("Đề cương MH được công bố công khai, giúp bạn dễ dàng tiếp cận");
        listCH.add("Bạn đã nhận được thông tin cần thiết về mục tiêu, chuẩn đầu ra, lịch trình MH, phương pháp học tập, và kiểm tra, đánh giá kết quả học tập khi bắt đầu MH");
        listCH.add("Tài liệu do GV cung cấp hoặc giới thiệu mang tính cập nhật, đáp ứng nhu cầu học tập MH của bạn");
        listCH.add("GV luôn chuẩn bị tốt bài giảng trước khi lên lớp");
        listCH.add("Nội dung MH thiết thực, hữu ích và vừa sức đối với bạn");
        listCH.add("GV nắm vững kiến thức MH, kiến thức thực tiễn và kiến thức các MH liên quan");
        listCH.add("GV có phương pháp truyền đạt rõ ràng, dễ hiểu và giải đáp thỏa đáng những thắc mắc của SV liên quan đến MH");
        listCH.add("GV đã hướng dẫn phương pháp học tập sao cho đạt hiệu quả và thúc đẩy việc tự học của bạn");
        listCH.add("GV đã hướng dẫn bạn thực hiện tốt các bài tập, tiểu luận, thực hành, thí nghiệm… của MH này");
        listCH.add("Bạn cảm thấy hứng thú khi học tập MH này");
        listCH.add("Việc đánh giá kết quả học tập phù hợp với đặc thù MH và đảm bảo tính khách quan, công bằng, phản ánh đúng năng lực của SV");
        listCH.add("Kết quả đánh giá được phản hồi kịp thời để SV cải thiện việc học tập");
        listCH.add("GV thường xuyên lên lớp đúng giờ, dạy đủ thời lượng và đảm bảo thực hiện đúng thời khóa biểu");
        listCH.add("GV luôn thể hiện rõ sự nhiệt tình và tinh thần trách nhiệm cao trong giảng dạy");
        listCH.add("GV có tác phong sư phạm chuẩn mực và ứng xử đúng mực với SV");
        listCH.add("GV quan tâm đến giáo dục đạo đức, nhân cách, ý thức tổ chức kỉ luật cho SV");
        listCH.add("Nhìn chung, sĩ số của lớp tham gia MH này hầu như rất đầy đủ");
        listCH.add("Bạn đã lĩnh hội được những kiến thức cơ bản của MH và đạt được các kĩ năng thực hành có thể cần thiết cho tương lai");
        listCH.add("Nhìn chung bạn hài lòng với MH này");
        for (int i = 0; i < 19; i++) {
            listDiemKS[i] = 0;
        }
        ks = new KhaoSat();
        Intent in = getIntent();
        if (in != null) {
            Bundle bundle = in.getExtras();
            subjectID = bundle.getString("subjectID");
        }
        btPre.setEnabled(false);
        btNext.setEnabled(false);
    }

    public void event() {
        getListKQHT();
        arr = new String[listKQHT.size()];
        for (int i = 0; i < listKQHT.size(); i++) {
            arr[i] = listKQHT.get(i).SubjectName;
        }
        if (listKQHT.size() > 0) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
            sp.setAdapter(adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    subjectID = listKQHT.get(i).SubjectID;
                    page = 0;
                    btNext.setEnabled(true);
                    loadCH(page);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page < 3) {
                    btPre.setEnabled(true);
                    page++;
                }
                if (page == 3) btNext.setEnabled(false);
                getDiemKS(rg1, 0);
                getDiemKS(rg2, 1);
                getDiemKS(rg3, 2);
                getDiemKS(rg4, 3);
                getDiemKS(rg5, 4);
                loadCH(page);
            }
        });
        btPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 0) {
                    btNext.setEnabled(true);
                    page--;
                }
                if (page == 0) btPre.setEnabled(false);
                loadCH(page);
            }
        });

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDone = true;
                for (int i = 0; i < 19; i++) {
                    if (listDiemKS[i] == 0) {
                        isDone = false;
                        break;
                    }
                }
                if (isDone) {
                    ks = new KhaoSat(LoginInf.studentID, listDiemKS[0], listDiemKS[1], listDiemKS[2], listDiemKS[3], listDiemKS[4], listDiemKS[5], listDiemKS[6], listDiemKS[7], listDiemKS[8], listDiemKS[9], listDiemKS[10], listDiemKS[11], listDiemKS[12], listDiemKS[13], listDiemKS[14], listDiemKS[15], listDiemKS[16], listDiemKS[17], listDiemKS[18], ct20.getText().toString().trim(), subjectID);
                    UpdateDanhGia(subjectID);
                    if (Insert(ks)) {
                        Toast.makeText(KhaoSatActivity.this, "Lưu bản ghi khảo sát chất lượng học phần thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KhaoSatActivity.this, "Chưa thực hiện khảo sát hết tất cả các tiêu chí!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadCH(int page) {
        for (int i = 0; i < listCH.size(); i++) {
            if (page < 3) {
                if ((i % 5) == 0 && (i / 5) == page) {
                    ch1.setText(listCH.get(i));
                    setRB(rb11, rb12, rb13, rb14, listDiemKS[i]);
                }
                if ((i % 5) == 1 && (i / 5) == page) {
                    ch2.setText(listCH.get(i));
                    setRB(rb21, rb22, rb23, rb24, listDiemKS[i]);
                }
                if ((i % 5) == 2 && (i / 5) == page) {
                    ch3.setText(listCH.get(i));
                    setRB(rb31, rb32, rb33, rb34, listDiemKS[i]);
                }
                if ((i % 5) == 3 && (i / 5) == page) {
                    ch4.setText(listCH.get(i));
                    setRB(rb41, rb42, rb43, rb44, listDiemKS[i]);
                }
                if ((i % 5) == 4 && (i / 5) == page) {
                    ch5.setText(listCH.get(i));
                    setRB(rb51, rb52, rb53, rb54, listDiemKS[i]);
                }
            } else {
                if ((i % 5) == 0 && (i / 5) == page) {
                    ch1.setText(listCH.get(i));
                    setRB(rb11, rb12, rb13, rb14, listDiemKS[i]);
                }
                if ((i % 5) == 1 && (i / 5) == page) {
                    ch2.setText(listCH.get(i));
                    setRB(rb21, rb22, rb23, rb24, listDiemKS[i]);
                }
                if ((i % 5) == 2 && (i / 5) == page) {
                    ch3.setText(listCH.get(i));
                    setRB(rb31, rb32, rb33, rb34, listDiemKS[i]);
                }
                if ((i % 5) == 3 && (i / 5) == page) {
                    ch4.setText(listCH.get(i));
                    setRB(rb41, rb42, rb43, rb44, listDiemKS[i]);
                }
            }

        }
    }

    public void setRB(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, int diemks) {
        switch (diemks) {
            case 0:
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case 1:
                rb1.setChecked(true);
                break;
            case 2:
                rb2.setChecked(true);
                break;
            case 3:
                rb3.setChecked(true);
                break;
            case 4:
                rb4.setChecked(true);
                break;
        }
    }

    public void getDiemKS(RadioGroup rg, int pos) {
        RadioButton rd = findViewById(rg.getCheckedRadioButtonId());
        String txt = rd.getText().toString().trim();
        switch (txt) {
            case ".1":
                listDiemKS[page * 5 + pos] = 1;
                break;
            case ".2":
                listDiemKS[page * 5 + pos] = 2;
                break;
            case ".3":
                listDiemKS[page * 5 + pos] = 3;
                break;
            case ".4":
                listDiemKS[page * 5 + pos] = 4;
                break;
        }
    }

    public boolean Insert(KhaoSat ks) {
        try {
            Statement statement = connection.createStatement();
            String sql = "insert into KhaoSat values(" + ks.toString() + ")";
            if (statement.executeUpdate(sql) > 0) {
                statement.close();
                return true;
            } else {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UpdateDanhGia(String subjectID) {
        try {
            Statement statement = connection.createStatement();
            String sql = "update KQHT set isDanhGia = 1 where SubjectID = '" + subjectID + "'";
            if (statement.executeUpdate(sql) > 0) {
                statement.close();
                return true;
            } else {
                statement.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getListKQHT() {
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from KQHT where StudentID = '" + LoginInf.studentID + "' and isDanhGia = 0";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                listKQHT.add(new KQHT(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), (double) rs.getFloat(8), (double) rs.getFloat(9), (double) rs.getFloat(10), rs.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

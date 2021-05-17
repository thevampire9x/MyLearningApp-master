package com.example.mylearningapp.Model;

public class KQHT {
    public String StudentID, SubjectID, SubjectName, TeacherName, NamHoc, HocKy;
    public int SoTC, HeSo, isDanhGia;
    public double DiemTP, DiemKT, DiemTBHP;

    public KQHT() {
    }

    public KQHT(String studentID, String subjectID, String subjectName, String teacherName, String namHoc, String hocKy, int soTC, int heSo, double diemTP, double diemKT, double diemTBHP, int isDanhGia) {
        StudentID = studentID;
        SubjectID = subjectID;
        SubjectName = subjectName;
        TeacherName = teacherName;
        SoTC = soTC;
        HeSo = heSo;
        this.isDanhGia = isDanhGia;
        DiemTP = diemTP;
        DiemKT = diemKT;
        DiemTBHP = diemTBHP;
        NamHoc = namHoc;
        HocKy = hocKy;
    }
}

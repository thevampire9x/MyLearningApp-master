package com.example.mylearningapp.Model;

public class Schedule {
    public String StudentID, SubjectID, SubjectName, TeacherName, ClassID, Weeks, Ngay, Tiet, Namhoc;
    public int SoTC, Phong;

    public Schedule() {
    }

    public Schedule(String studentID, String subjectID, String subjectName, String teacherName, int soTC, String classID, String weeks, String ngay, String tiet, int phong, String Namhoc) {
        StudentID = studentID;
        SubjectID = subjectID;
        SubjectName = subjectName;
        TeacherName = teacherName;
        ClassID = classID;
        Weeks = weeks;
        Ngay = ngay;
        Tiet = tiet;
        SoTC = soTC;
        Phong = phong;
        this.Namhoc = Namhoc;
    }
}

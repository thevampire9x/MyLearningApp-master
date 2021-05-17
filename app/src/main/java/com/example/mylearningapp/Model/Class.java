package com.example.mylearningapp.Model;

public class Class {
    public String ClassID, FacultyID, Year, Period, TeacherID;
    public int Total;

    public Class() {
    }

    public Class(String classID, String facultyID, int total, String year, String period, String teacherID) {
        ClassID = classID;
        FacultyID = facultyID;
        Year = year;
        Period = period;
        TeacherID = teacherID;
        Total = total;
    }
}

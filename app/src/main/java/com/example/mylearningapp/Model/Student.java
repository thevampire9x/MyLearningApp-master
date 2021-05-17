package com.example.mylearningapp.Model;

public class Student {
    public String StudentID, ClassID, FullName, Birthday, Address, Email, PhoneNumber, Password, Photo;
    public int Gender;

    public Student() {
    }

    public Student(String studentID, String classID, String fullName, int gender, String birthday, String address, String email, String phoneNumber, String password, String photo) {
        StudentID = studentID;
        ClassID = classID;
        FullName = fullName;
        Birthday = birthday;
        Address = address;
        Email = email;
        PhoneNumber = phoneNumber;
        Password = password;
        Photo = photo;
        Gender = gender;
    }
}

package com.example.mylearningapp.Model;

public class KhaoSat {
    public String StudentID, CT20, SubjectID;
    public int CT1, CT2, CT3, CT4, CT5, CT6, CT7, CT8, CT9, CT10, CT11, CT12, CT13, CT14, CT15, CT16, CT17, CT18, CT19;

    public KhaoSat() {
    }

    public KhaoSat(String studentID, int CT1, int CT2, int CT3, int CT4, int CT5, int CT6, int CT7, int CT8, int CT9, int CT10, int CT11, int CT12, int CT13, int CT14, int CT15, int CT16, int CT17, int CT18, int CT19, String CT20, String subjectID) {
        StudentID = studentID;
        this.CT20 = CT20;
        this.CT1 = CT1;
        this.CT2 = CT2;
        this.CT3 = CT3;
        this.CT4 = CT4;
        this.CT5 = CT5;
        this.CT6 = CT6;
        this.CT7 = CT7;
        this.CT8 = CT8;
        this.CT9 = CT9;
        this.CT10 = CT10;
        this.CT11 = CT11;
        this.CT12 = CT12;
        this.CT13 = CT13;
        this.CT14 = CT14;
        this.CT15 = CT15;
        this.CT16 = CT16;
        this.CT17 = CT17;
        this.CT18 = CT18;
        this.CT19 = CT19;
        this.SubjectID = subjectID;
    }

    @Override
    public String toString() {
        return  "'" + StudentID +
                "', " + CT1 +
                ", " + CT2 +
                ", " + CT3 +
                ", " + CT4 +
                ", " + CT5 +
                ", " + CT6 +
                ", " + CT7 +
                ", " + CT8 +
                ", " + CT9 +
                ", " + CT10 +
                ", " + CT11 +
                ", " + CT12 +
                ", " + CT13 +
                ", " + CT14 +
                ", " + CT15 +
                ", " + CT16 +
                ", " + CT17 +
                ", " + CT18 +
                ", " + CT19 +
                ", '" + CT20  +
                "', '"+ SubjectID +
                "'";
    }
}

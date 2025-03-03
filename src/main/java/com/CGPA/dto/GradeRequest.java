package com.CGPA.dto;

public class GradeRequest {
    private int grade;

    public GradeRequest(int g){
        this.grade = g;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int g) {
        this.grade = g;
    }
}

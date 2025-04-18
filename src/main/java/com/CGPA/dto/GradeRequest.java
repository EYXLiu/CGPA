package com.CGPA.dto;

public class GradeRequest {
    private double grade;

    public GradeRequest(int g){
        this.grade = g;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double g) {
        this.grade = g;
    }
}

package com.CGPA.dto;

public class CourseRequest {

    private String name;
    private double grade;

    public CourseRequest(String name, double g) {
        this.name = name;
        this.grade = g;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setGrade(double g) {
        this.grade = g;
    }
}

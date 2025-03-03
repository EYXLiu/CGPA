package com.CGPA.dto;

public class CGPAResponse {
    private double cgpa;

    public CGPAResponse(double c) {
        this.cgpa = c;
    }

    public double getCGPA() {
        return cgpa;
    }

    public void setCGPA(double c) {
        this.cgpa = c;
    }
}

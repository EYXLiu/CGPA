package com.CGPA.service;

import com.CGPA.dto.GradeRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CGPACalculate {
    private double percentToGPA(double percentage) {
        if (percentage >= 90) return 4.00;
        else if (percentage >= 85) return 3.90;
        else if (percentage >= 90) return 3.70;
        else if (percentage >= 77) return 3.30;
        else if (percentage >= 73) return 3.00;
        else if (percentage >= 70) return 2.70;
        else if (percentage >= 67) return 2.30;
        else if (percentage >= 63) return 2.00;
        else if (percentage >= 60) return 1.70;
        else if (percentage >= 56) return 1.00;
        else return 0.00;
    }

    public double calculateCGPA(List<GradeRequest> grades) {
        double total = 0;

        for (GradeRequest entry : grades) {
            double gpa = percentToGPA(entry.getGrade());
            total += gpa;
        }
        
        return (grades.size() == 0) ? 0.0 : total / grades.size();
    }
}

package com.CGPA.controller;

import com.CGPA.dto.GradeRequest;
import com.CGPA.dto.CGPAResponse;
import com.CGPA.service.CGPACalculate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculate")
@CrossOrigin(origins = "*")
public class CGPAController {
    private final CGPACalculate calc;

    public CGPAController(CGPACalculate c) {
        this.calc = c;
    }

    @PostMapping
    @ResponseBody
    public CGPAResponse calculateCGPA(@RequestBody List<GradeRequest> grades) {
        double cgpa = calc.calculateCGPA(grades);
        return new CGPAResponse(cgpa);
    }
}

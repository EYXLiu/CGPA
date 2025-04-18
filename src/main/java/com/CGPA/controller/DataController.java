package com.CGPA.controller;

import org.springframework.stereotype.Controller;

import com.CGPA.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import com.CGPA.entity.Grade;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class DataController {
    
    private final JwtUtil jwtUtil;

    public DataController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    @GetMapping("/grades")
    @ResponseBody
    public List<Grade> getGrades(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    String jwtToken = cookie.getValue();
                    return jwtUtil.getGrades(jwtToken);
                }
            }
        }
        return List.of();
    }

}

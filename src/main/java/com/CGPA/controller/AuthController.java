package com.CGPA.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.CGPA.dto.CourseRequest;
import com.CGPA.entity.Grade;
import com.CGPA.service.AuthService;
import com.CGPA.util.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    private String getJwtFromString(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        authService.register(request.get("name"), request.get("email"), request.get("password"));
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Map<String, String> request, HttpServletResponse response) {
            boolean success = authService.login(request.get("email"), request.get("password"));
            if (success) {
                String jwt = jwtUtil.generate(request.get("email"));
                Cookie cookie = new Cookie("jwt", jwt);
                cookie.setHttpOnly(true);
                //cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(24*60*60);

                response.addCookie(cookie);
                return ResponseEntity.ok("Login successful");

            }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid Credentials"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody List<CourseRequest> grades, HttpServletRequest request, HttpServletResponse response) {
        String jwt = getJwtFromString(request);
        List<Grade> grades2 = new ArrayList<>();
        for (CourseRequest c : grades) {
            System.out.println(c.getName());
            grades2.add(new Grade(c.getName(), c.getGrade(), null));
        }
        if (jwt != null) {
            String email = jwtUtil.getEmail(jwt);
            jwtUtil.save(grades2, email);

            Cookie cookie = new Cookie("jwt", "");
            cookie.setHttpOnly(true);
            //cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);

            response.addCookie(cookie);

            return ResponseEntity.ok("Logged out successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No JWT token found");
    }
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody List<CourseRequest> grades, HttpServletRequest request, HttpServletResponse response) {
        String jwt = getJwtFromString(request);
        List<Grade> grades2 = new ArrayList<>();
        for (CourseRequest c : grades) {
            System.out.println(c.getName());
            grades2.add(new Grade(c.getName(), c.getGrade(), null));
        }
        if (jwt != null) {
            String email = jwtUtil.getEmail(jwt);
            jwtUtil.save(grades2, email);

            return ResponseEntity.ok("Data saved successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No JWT token found");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getMethodName(HttpServletRequest request) {
        String jwt = getJwtFromString(request);
        if (jwt != null && jwtUtil.validateToken(jwt)) {
            return ResponseEntity.ok("JWT token found");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No JWT token found");
    }
    
    @GetMapping("/reset")
    public ResponseEntity<?> reset(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out successfully");
    }
    
}

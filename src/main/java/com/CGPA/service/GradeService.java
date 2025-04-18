package com.CGPA.service;

import com.CGPA.entity.User;
import com.CGPA.entity.Grade;
import com.CGPA.repository.UserRepository;

import jakarta.transaction.Transactional;

import com.CGPA.repository.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGrades(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return gradeRepository.findByUser(user);
    }

    @Transactional
    public void saveGrades(String email, Grade grade) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

        grade.setUser(user);
        
        user.getGrades().add(grade);
        userRepository.save(user);
    }
}

package com.CGPA.repository;

import com.CGPA.entity.Grade;
import com.CGPA.entity.User;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByUser(User user);
}

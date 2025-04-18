package com.CGPA.util;

import io.jsonwebtoken.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.CGPA.entity.Grade;
import com.CGPA.service.GradeService;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private final long EXPIRATION = 1000 * 60 * 60;

    @Autowired
    private GradeService gradeService;

    public String generate(String email) {
        List<Grade> g = gradeService.getGrades(email);
        System.out.println(g.get(0).getSubject());
        String token = Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(SignatureAlgorithm.HS256, secret)
            .claim("grades", g)
            .compact();
        return token;
    }

    public List<Grade> getGrades(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody();
        
        @SuppressWarnings("unchecked")
        List<Grade> g = (List<Grade>) claims.get("grades");

        return g;
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public void save(List<Grade> grades, String email) {
        for (Grade g : grades) {
            gradeService.saveGrades(email, g);
        }
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

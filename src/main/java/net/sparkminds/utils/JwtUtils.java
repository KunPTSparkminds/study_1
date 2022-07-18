package net.sparkminds.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sparkminds.entity.Reviewer;

@Component
public class JwtUtils {
    private static String secrect = "Secrect";
    public String generateJwt (Reviewer reviewer) {
        
        Date issuedAt = new Date();
        
        Claims claims = Jwts.claims().setIssuer(Long.toString(reviewer.getId())).setIssuedAt(issuedAt);
        return Jwts.builder().setClaims(claims).compact();
        
        
    }
}

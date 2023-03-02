package com.dev.videoblogappcore.configuration;

import com.dev.videoblogappcore.exceptions.VideoBlogException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY="423F4528482B4D6251655468576D597133743677397A24432646294A404E6352";

    public String validateToken(String token) {
       try{
           return getUsernameFromToken(token) ;
       }catch (Exception e) {
           throw new VideoBlogException(511, "Token expiró");
       }
    }

    public String getUsernameFromToken(String token){
        return getClaims(token).getSubject();
    }


    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigninKey(){
        byte[] secretKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretKey);
    }





}

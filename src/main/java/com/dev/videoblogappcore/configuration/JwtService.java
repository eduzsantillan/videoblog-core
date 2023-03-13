package com.dev.videoblogappcore.configuration;

import com.dev.videoblogappcore.exceptions.VideoBlogException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
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


    public String getUserNameFromToken(String authorization) throws FirebaseAuthException {
        FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(authorization);
        return token.getEmail();
    }

}

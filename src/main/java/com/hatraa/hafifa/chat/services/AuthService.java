package com.hatraa.hafifa.chat.services;

import com.hatraa.hafifa.chat.model.User;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.jose4j.lang.ByteUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.servlet.http.HttpServletRequest;
import java.security.*;
import java.util.Date;

@Service("authService")
public class AuthService {
    private Key _secret;

    public AuthService() throws NoSuchAlgorithmException {
        _secret = MacProvider.generateKey();
    }

    public String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public String getSalt() {
        return BCrypt.gensalt();
    }

    public String encodeUser(String id) {
        return Jwts.builder().setSubject(id).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, _secret).compact();
    }

    public Claims decodeUser (String token) throws SignatureException {
        return Jwts.parser().setSigningKey(_secret).parseClaimsJws(token).getBody();
    }
}

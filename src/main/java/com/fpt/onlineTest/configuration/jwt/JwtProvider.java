package com.fpt.onlineTest.configuration.jwt;


import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.model.Teacher;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.fpt.onlineTest.model.User;
import com.fpt.onlineTest.service.AdminService;
import com.fpt.onlineTest.service.TeacherService;
import com.fpt.onlineTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;


@Component
public class JwtProvider {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Value("${jwt.secret_key}")
    private String secretKey;
    public String generateTokenLoginUser(String username) {
        String token = null;
        try {
            User user = userService.findByUsername(username);
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("Username", username);
            builder.expirationTime(generateExpirationTime());
            String[] roles = {"USER"};
            builder.claim("ROLES",roles);
//            builder.claim("username",user.getFirstName());
//            builder.claim("lastName",user.getLastName());
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public String generateTokenLoginAdmin(String username) {
        String token = null;
        try {
            Admin admin = adminService.findByUsername(username);
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("Username", username);
            builder.expirationTime(generateExpirationTime());
            String[] roles = {"ADMIN"};
            builder.claim("ROLES",roles);
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
    public String generateTokenLoginTeacher(String username) {
        String token = null;
        try {
            Teacher teacher = teacherService.findByUsername(username);
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("Username", username);
            builder.expirationTime(generateExpirationTime());
            String[] roles = {"TEACHER"};
            builder.claim("ROLES",roles);
            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public JWTClaimsSet getClaimsSetFromToken(String token) {
        JWTClaimsSet claimsSet = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());

            if (signedJWT.verify(verifier)) {
                claimsSet = signedJWT.getJWTClaimsSet();
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing exception
        } catch (JOSEException e) {
            e.printStackTrace();
            // Handle JOSE exception
        }
        return claimsSet;
    }

    private Date generateExpirationTime() {
        long expireTime =1000000000000000L; //Constants.JWT_EXPIRATION_TIME;
        Date currentDate= new Date();
        Date expDate = new Date(currentDate.getTime()+expireTime);
        return expDate;
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        JWTClaimsSet claimsSet = getClaimsSetFromToken(token);
        expiration = claimsSet.getExpirationTime();
        return expiration;
    }

    public String getUsernameFromToken(String token) {
        if(token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }

        String username = null;
        try {
            JWTClaimsSet claimsSet = getClaimsSetFromToken(token);
            username = claimsSet.getStringClaim("Username");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    private byte[] generateShareSecret() {
        byte[] sharedSecret = new byte[32];
        sharedSecret = secretKey.getBytes();
        return sharedSecret;
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateTokenLogin(String token) {
        if (token == null || token.trim().length() == 0) return false;
        String username = getUsernameFromToken(token);
        if (username == null || username.isEmpty()) return false;
        if (isTokenExpired(token)) return false;
        return true;
    }
}

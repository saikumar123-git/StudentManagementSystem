package com.example.student.Configurations;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class GenerateJwtKey {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Your Base64 Secret Key: " + base64Key);
    }
}

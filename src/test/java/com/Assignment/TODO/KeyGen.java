package com.Assignment.TODO;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGen {
    @Test
    void keyGen(){
        SecretKey build = Jwts.SIG.HS512.key().build();
        String s = Base64.getEncoder().encodeToString(build.getEncoded());
        System.out.println(s);
    }
}

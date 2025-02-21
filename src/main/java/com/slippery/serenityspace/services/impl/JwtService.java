package com.slippery.serenityspace.services.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String generationString ="07fa644cc361f93fd34a1a0ab6edd9b6ac86d5ee8438a6e5c10fa4a2e3ab191d4c409cf15358e6656cdb63ab004bfac22fab33eb8e082d4f157de026abd622e4309adc6488c0983e7f911fbeb6318f2d36ed7aec09e75f0b40edb4b95b6360f0b95f65ab96ce83d04d425079407416d1422502cd14aa8065f19dbc449458f4a6dbbfa443c2ca623f0695604171a71cb0367b2678074aadb86739ceaaefb0856e5fd6acd479fbfedd01d84c02b774bbe68b03c131ec1bcef46f6ce7f8a1d5a6df49ae5c83d4c6fd1e69dfc732d4855cc484ff45df687ad85238a4581613b62491154563a0b2ec28e2e4d5b243e2478f5a15be9c5cd9bd79afd57501f5742bba9b";
    private final Long expirationTime =8640000L;

    private SecretKey generateSignInKey(){
        byte[] keyBytes = Base64.getDecoder().decode(generationString);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims =new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .and()
                .signWith(generateSignInKey())
                .expiration(new Date(System.currentTimeMillis()+expirationTime))
                .compact();
    }

}

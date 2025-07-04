package com.alexandersaul.authService.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JWTUtils {

    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);
        String userName = authentication.getPrincipal().toString();

        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiration = new Date(System.currentTimeMillis() + (60 * 60 * 1000));

        return JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(userName)
                .withClaim("authorities", authorities)
                .withIssuedAt(now)
                .withNotBefore(now)
                .withExpiresAt(expiration)
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }


    public DecodedJWT validateToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Invalid token, Not authorized");
        }

    }

    public String extractUserName (DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim (DecodedJWT decodedJWT , String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String , Claim> returnAllClaims (DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }


}

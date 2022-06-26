package com.project.crewwebproject.jwt;


import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.StatusCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String TOKEN_TYPE_KEY = "type";
    private static final String NICKNAME_KEY = "nick";

    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;

    private Key key;

    //application-local.yml 에 정의해놓은 jwt.secret 값을 가져와서 JWT 를 만들 때 사용하는 암호화 키값을 생성합니다.
    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
            @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //유저 정보를 넘겨받아서 Access Token을 생성합니다.
    //TODO
    //Refresh Token 도 제작 필요
    public String createAccessToken(String memberId, String nickname) {
        String authorities = "ROLE_USER";

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.accessTokenValidityInMilliseconds);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(memberId)
                .setIssuer("LoveClinic Dear")
                //.setIssuedAt(new Date())
                .setExpiration(validity)
                .claim(AUTHORITIES_KEY, authorities)
                .claim(NICKNAME_KEY, nickname)
                .claim(TOKEN_TYPE_KEY, "access")
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    

    //JWT 토큰을 복호화하여 토큰에 들어 있는 정보를 꺼냅니다
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new PrivateException(StatusCode.LOGIN_WRONG_SIGNATURE_JWT_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new PrivateException(StatusCode.LOGIN_EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException e) {
            throw new PrivateException(StatusCode.LOGIN_NOT_SUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new PrivateException(StatusCode.LOGIN_WRONG_FORM_JWT_TOKEN);
        }
    }
}

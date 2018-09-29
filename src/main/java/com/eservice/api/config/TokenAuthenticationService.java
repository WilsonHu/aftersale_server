package com.eservice.api.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

/**
 * @author Wu Xuemin
 * @Date 2018/07/09.
 */
class TokenAuthenticationService {
    /**
     * 不用L型数值会溢出, 30 days
     */
    static final long EXPIRATIONTIME = 1000L*60*60*24*30;
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    /**
     *   建立JWT，用户信息，并加到response里
     */
    static void addAuthentication(HttpServletResponse res, String user) {
        String JWT = Jwts.builder()
                .setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.setCharacterEncoding("UTF-8");
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        try {
            res.getWriter().write(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *   从request的 TOKEN 里解析得到用户信息
     */
    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            return user != null ? new UsernamePasswordAuthenticationToken(user, null, emptyList()) : null;
        }
        return null;
    }
}
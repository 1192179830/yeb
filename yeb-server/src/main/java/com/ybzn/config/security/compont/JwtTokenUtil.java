package com.ybzn.config.security.compont;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken工具类
 *
 * @author Hugo
 * @since 1.0.0
 */
@Component
public class JwtTokenUtil {

    private final String CLAIM_KEY_USERNAME = "sub";
    private final String CLAIM_KEY_CREATED = "created";
    @Value ("${jwt.secret}")
    private String secret;
    @Value ("${jwt.expiration}")
    private Long expiration;


    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    public String generateToken (UserDetails userDetails) {
        Map <String, Object> claims = new HashMap <>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /**
     * 根据token获取用户名
     *
     * @param token
     * @return
     */
    public String getUserNameFormToken (String token) {
        String username;
        try{
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        }catch (Exception e){
            username=null;
        }
        return username;
    }


    /**
     * 判断token是否有效
     *
     * @param token
     * @return
     */
    public boolean validateToken (String token, UserDetails userDetails) {
        String username = getUserNameFormToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpiration(token);
    }

    /**
     * 判断token是否可以刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh (String token) {
        return !isTokenExpiration(token);
    }


    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public String refreshToken (String token) {
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /**
     * 判断token是否失效
     *
     * @param token
     * @return
     */
    private boolean isTokenExpiration (String token) {
        Date expDate = getExpirationFromToken(token);
        return expDate.before(new Date());
    }

    /**
     * 从token中获取失效时间
     *
     * @param token
     * @return
     */
    private Date getExpirationFromToken (String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }


    /**
     * 根据token获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFormToken (String token) {
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims=null;
            e.printStackTrace();
        }
        return claims;
    }


    /**
     * 根据荷载生成token
     *
     * @param claims
     * @return
     */
    private String generateToken (Map <String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(generateExpiration())
                .compact();
    }

    /**
     * 生成失效时间
     *
     * @return
     */
    private Date generateExpiration () {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}

package cn.com.small_design.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author gejj
 * @createTime 2024年03月25日 16:16
 * @version 1.0
 */
public class JwtUtils {

    /**
     * 过期时间
     */
    public static final long JWT_TTL = 60*60*1000L;

    /**
     * 密钥明文
     */
    public static final String JWE_KEY = "";

    /**
     * 获取uuid
     * @return
     */
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }

    public static String createJWT(String subject){
        //设置过期时间
        JwtBuilder builder = getJwtBuilder(subject,null,getUUID());
        return builder.compact();
    }

    public static String createJWT(String id,String subject,Long ttlMillis){
        JwtBuilder builder = getJwtBuilder(subject,ttlMillis,id);
        return builder.compact();
    }

    public static SecretKey generalKey(){
        byte[] decode = Base64.getDecoder().decode(JWE_KEY);
        SecretKeySpec ase = new SecretKeySpec(decode, 0, decode.length, "ASE");
        return ase;
    }

    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("small")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}

package cn.com.small_design.common.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author gejj
 * @create 2024年03月25日 16:16
 * @version 1.0
 */
@Component
public class JwtUtils {

    /**
     * 过期时间
     */
    public static final long JWT_TTL = 60*60*1000L;

    /**
     * 密钥明文
     */
    public static final String JWE_KEY = "design";

    /**
     * 获取uuid
     * @return
     */
    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-","");
        return token;
    }

    public static String createJwt(String object,String userId){
        JwtBuilder jwtBuilder = getJwtBuilder(object, getUUID(), userId);
        return jwtBuilder.compact();
    }

    /**
     * 生成加密后的密钥
     * @return
     */
    public static SecretKey generalKey(){
        byte[] decode = Base64.getDecoder().decode(JWE_KEY);
        SecretKeySpec ase = new SecretKeySpec(decode, 0, decode.length, "ASE");
        return ase;
    }

    /**
     * 生成jwt
     * @param subject
     * @param uuid
     * @param userId
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, String uuid, String userId) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        long expMillis = nowMillis + JWT_TTL;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("small")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate)
                .claim("userId",userId);
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

package cn.com.small_design.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author gejj
 * @version 1.0
 * @create 2024年04月29日 16:44
 */

@Component
public class BCryptPasswordUtil {

    /**
     * 密码加密方式
     * @param password
     * @return
     */
    public String bcryptPassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }
}

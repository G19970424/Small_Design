package cn.com.small_design.common.utils;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.dao.dao.pojo.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author gejj
 * @create 2024年04月29日 14:43
 * @version 1.0
 *
 */
@Component
public class SecurityUtil {

    /**
     * 获取当前登录用户信息
     * @return
     */
    public User getLocalUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken){
            throw new BusinessException("用户未登录！");
        }

        return (User) authentication.getPrincipal();
    }

}

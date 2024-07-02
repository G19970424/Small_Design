package cn.com.small_design.service.common;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.utils.JwtUtil;
import cn.com.small_design.common.utils.RedisUtil;
import cn.com.small_design.dao.dao.PermissionMapper;
import cn.com.small_design.dao.dao.UserMapper;
import cn.com.small_design.dao.dao.pojo.Permission;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author gejj
 * @create 2024年03月25日 15:22
 * @version 1.0
 */
@Service("userDetailService")
public class UserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String s) {

        if(s == null || "".equals(s)){
            throw new BusinessException(GlobalExceptionEnums.NOT_NULL_USERNAME);
        }

        //用户信息查询
        User user = UserMapper.queryUserByUsername(s);

        //判断用户是否存在
        if(Objects.isNull(user)){
            throw new BusinessException(GlobalExceptionEnums.INCORRECT_USERNAME_OR_PASSWORD);
        }

        //用户权限信息查询
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = permissionMapper.queryByUser(user.getId());
        permissions.forEach(code ->{
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(code.getCode());
            authorities.add(simpleGrantedAuthority);
        });
        return new UserInfo(user,authorities);
    }
}

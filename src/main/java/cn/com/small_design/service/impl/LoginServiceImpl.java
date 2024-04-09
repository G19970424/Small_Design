package cn.com.small_design.service.impl;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.utils.JwtUtils;
import cn.com.small_design.common.utils.RedisUtils;
import cn.com.small_design.controller.base.dto.UserDto;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author gejj
 * @version 1.0
 * @createTime 2024年04月02日 14:03
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public User login(UserDto userDto) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(up);

        //用户认证失败
        if(Objects.isNull(authenticate)){
            logger.info("用户名/密码错误");
            throw new RuntimeException("用户名/密码错误");
        }
        UserInfo userInfo = (UserInfo) authenticate.getPrincipal();

        String id = userInfo.getUser().getId();

        //用户认证通过
        String jwt = JwtUtils.createJwt("userId", id);
        redisUtils.setCacheObject("login:"+id,userInfo);
        return null;
    }
}

package cn.com.small_design.service.impl;

import cn.com.small_design.common.common.UserInfo;
import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.utils.JwtUtil;
import cn.com.small_design.common.utils.RedisUtil;
import cn.com.small_design.controller.base.dto.UserDto;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
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
 * @create 2024年04月02日 14:03
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements ILoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisUtil redisUtils;

    private final String CAPTCHA_KEY = "captcha:verification:";
    @Override
    public String login(UserDto userDto) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(up);

        //获取验证码
        Object captcha = redisUtils.getCacheObject(CAPTCHA_KEY+userDto.getCaptchaKey());

        //用户认证校验
        if(Objects.isNull(captcha) || !userDto.getCaptcha().equals(captcha)){
            throw new BusinessException(GlobalExceptionEnums.CAPTCHA_ERROR);
        }

        //用户认证失败
        if(Objects.isNull(authenticate)){
            throw new BusinessException(GlobalExceptionEnums.INCORRECT_USERNAME_OR_PASSWORD);
        }

        //获取用户信息
        UserInfo userInfo = (UserInfo) authenticate.getPrincipal();

        String id = userInfo.getUser().getId();

        //用户认证通过，生成返回前端的jwt信息，用于进行用户信息权限验证
        String jwt = JwtUtil.createJwt("userId", id);

        //将用户信息缓存到redis中
        redisUtils.setCacheObject("login:"+id,userInfo);
        return jwt;
    }
}

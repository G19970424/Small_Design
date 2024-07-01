package cn.com.small_design.service.impl;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.dao.dao.UserMapper;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import cn.com.small_design.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * @author gejj
 * @create  2024年04月09日 16:33
 * @version 1.0
 */

@Service
@Transactional
public class RegisterServiceImpl implements IRegisterService {

    @Autowired
    private UserMapper UserMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public RestResponse<?> register(RegisterDto registerDto){
        //验证用户信息是否存在
        User user = UserMapper.queryUserByUsername(registerDto.getUsername());

        if(!Objects.isNull(user)){
            throw new BusinessException(GlobalExceptionEnums.REPEAT_OF_USERNAME);
        }
        //对密码进行加密
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        //用户类型判定,当前系统默认为普通用户1
        user = conversion(registerDto);
        UserMapper.addUser(user);
        return ResultApi.ok();
    }

    private User conversion(RegisterDto registerDto){
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setLoginName(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        return user;
    }

}

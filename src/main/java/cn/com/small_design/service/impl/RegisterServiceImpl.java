package cn.com.small_design.service.impl;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.dao.dao.IUserMapper;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import cn.com.small_design.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private static final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private IUserMapper IUserMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public RestResponse<?> register(RegisterDto registerDto) {
        //验证用户信息是否存在
        User user = IUserMapper.queryUserByUsername(registerDto.getUsername());

        if(!Objects.isNull(user)){
            throw new BusinessException(GlobalExceptionEnums.REPEAT_OF_USERNAME);
        }
        //对密码进行加密
        registerDto.setPassword(encoder.encode(registerDto.getPassword()));
        //保存用户
        String userId = UUID.randomUUID().toString().replaceAll("-","");

        //用户类型判定,当前系统默认为普通用户1
        user = conversion(userId, registerDto);
        IUserMapper.addUser(user);
        return ResultApi.ok();
    }

    private User conversion(String id,RegisterDto registerDto){
        User user = new User();
        user.setId(id);
        user.setUsername(registerDto.getUsername());
        user.setLoginName(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setUserType('1');
        return user;
    }

}

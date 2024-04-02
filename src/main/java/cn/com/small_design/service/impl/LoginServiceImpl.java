package cn.com.small_design.service.impl;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.controller.base.bean.UserFormBean;
import cn.com.small_design.dao.dao.UserMapper;
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

    @Override
    public User login(UserFormBean userFormBean) {
        UsernamePasswordAuthenticationToken up = new UsernamePasswordAuthenticationToken(userFormBean.getUsername(),userFormBean.getPassword());
        Authentication authenticate = authenticationManager.authenticate(up);

        //用户认证失败
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名/密码错误");
        }

        //用户认证通过

        return null;
    }
}

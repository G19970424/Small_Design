package cn.com.small_design.service.impl;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.utils.BCryptPasswordUtil;
import cn.com.small_design.controller.manager.dto.UserManagerDto;
import cn.com.small_design.dao.dao.IUserMapper;
import cn.com.small_design.dao.dao.pojo.User;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import cn.com.small_design.service.IUserManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author gejj
 * @create 2024年04月29日 15:35
 * @version 1.0
 */
@Service
@Transactional
public class UserManagerServiceImpl implements IUserManagerService {
    /**
     * 日志器
     */
    private static final Logger logger = LoggerFactory.getLogger(UserManagerServiceImpl.class);

    @Autowired
    private IUserMapper userMapper;
    @Autowired
    private BCryptPasswordUtil bCryptPasswordUtil;

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> query() {
        return userMapper.queryAll();
    }

    /**
     * 新增用户
     * @param dto
     */
    @Override
    public void insert(UserManagerDto dto) {
        //查询登录名是否重复
        User user = userMapper.queryUserByUsername(dto.getLoginName());
        if(!Objects.isNull(user)){
            throw new BusinessException(GlobalExceptionEnums.REPEAT_OF_USERNAME);
        }
        //新增用户
        userMapper.addUser(conversion(dto));
    }

    /**
     * 更新用户
     * @param dto
     */
    @Override
    public void update(UserManagerDto dto) {
        //查询登录名是否重复
        User user = userMapper.queryUserByUsername(dto.getLoginName());
        if(!Objects.isNull(user)){
            throw new BusinessException(GlobalExceptionEnums.REPEAT_OF_USERNAME);
        }
        //更新用户
        userMapper.update(conversion(dto));
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @Override
    public void delete(String id) {

    }

    private User conversion(UserManagerDto dto){
        User user = new User();
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordUtil.bcryptPassword(dto.getPassword()));
        user.setLoginName(dto.getLoginName());
        user.setUserType(dto.getType());
        user.setPhoneNumber(dto.getPhone());
        user.setEmail(dto.getEmail());
        return user;
    }
}

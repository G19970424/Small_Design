package cn.com.small_design.dao.dao;

import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.dao.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author gejj
 * @createTime 2024年03月25日 15:40
 * @version 1.0
 */

@Mapper
@Repository
public interface UserMapper {
    /**
     * 通过用户登录名查询用户信息
     * @param username
     * @return
     */
    User queryUserByUsername(@Param("username")String username);

    /**
     * 添加用户
     * @param registerDto
     */
    void addUser(RegisterDto registerDto);
}

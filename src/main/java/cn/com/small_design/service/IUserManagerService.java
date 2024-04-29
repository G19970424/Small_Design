package cn.com.small_design.service;

import cn.com.small_design.controller.manager.dto.UserManagerDto;
import cn.com.small_design.dao.dao.pojo.User;

import java.util.List;

/**
 * @author gejj
 * @create 2024年04月29日 15:34
 * @version 1.0
 */
public interface IUserManagerService {
    List<User> query();

    void insert(UserManagerDto dto);

    void update(UserManagerDto dto);

    void delete(String id);
}

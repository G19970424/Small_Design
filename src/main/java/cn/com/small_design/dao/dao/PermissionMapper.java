package cn.com.small_design.dao.dao;

import cn.com.small_design.dao.dao.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Author gejj
 * @Date 2024/7/2 21:19
 * @Version 1.0
 **/
@Mapper
@Repository
public interface PermissionMapper {
    /**
     * 通过用户id 查询所有权限
     * @param id
     * @return
     */
    List<Permission> queryByUser(int id);
}

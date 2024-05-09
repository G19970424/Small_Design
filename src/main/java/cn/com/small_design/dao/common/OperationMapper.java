package cn.com.small_design.dao.common;

import cn.com.small_design.common.enums.OperationEnums;
import cn.com.small_design.dao.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * @author gejj
 * @create 2024年05月06日 14:00
 * @version 1.0
 */
@Mapper
@Repository
public interface OperationMapper {
    void insert(User user, OperationEnums enums, Object oldData,Object newData);
}

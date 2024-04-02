package cn.com.small_design.service;

import cn.com.small_design.controller.base.bean.UserFormBean;
import cn.com.small_design.dao.dao.pojo.User;

/**
 * @author gejj
 * @createTime 2024年03月25日 15:22
 * * @version 1.0
 */
public interface ILoginService {

    User login(UserFormBean userFormBean);
}

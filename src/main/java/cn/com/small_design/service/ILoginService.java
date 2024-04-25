package cn.com.small_design.service;

import cn.com.small_design.controller.base.dto.UserDto;

/**
 * @author gejj
 * @create 2024年03月25日 15:22
 * @version 1.0
 */
public interface ILoginService {

    String login(UserDto userDto);
}

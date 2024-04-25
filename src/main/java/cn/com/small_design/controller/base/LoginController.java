package cn.com.small_design.controller.base;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.controller.base.dto.UserDto;
import cn.com.small_design.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 *
 * 登录接口
 */
@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    /**
     * 用户登录接口实现
     * @param userDto 登录用户实体类
     * @return
     */
    @PostMapping("/login")
    public RestResponse login(@RequestBody UserDto userDto){
        return ResultApi.ok(loginService.login(userDto));
    }
}

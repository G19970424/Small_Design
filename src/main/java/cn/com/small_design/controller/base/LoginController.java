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

    @PostMapping("/login")
    public RestResponse login(@RequestBody UserDto userDto){
        RestResponse res;
        try {
            String jwt = loginService.login(userDto);
            res = ResultApi.ok(jwt);
        }catch (Exception e){
            res = ResultApi.fail(e.getMessage());
            logger.error(e.getMessage());
        }
        return res;
    }
}

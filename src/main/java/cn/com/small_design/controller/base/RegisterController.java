package cn.com.small_design.controller.base;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.controller.base.dto.RegisterDto;
import cn.com.small_design.service.IRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @createTime 2024年03月25日 14点59分
 * @version 1.0
 *
 * 注册接口
 */
@RestController
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private IRegisterService registerService;

    @RequestMapping("/register")
    public RestResponse register(@RequestBody RegisterDto registerDto){
        return registerService.register(registerDto);
    }
}

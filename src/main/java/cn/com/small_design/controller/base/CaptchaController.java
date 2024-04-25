package cn.com.small_design.controller.base;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.service.common.CaptchaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @create 2024年04月23日 15:50
 * @version 1.0
 */
@RestController
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);


    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/captcha")
    public RestResponse getCaptcha(){
        RestResponse res;
        try {
            res = ResultApi.ok(captchaService.cacheCaptcha());
        }catch (Exception e){
            res = ResultApi.fail("验证码生成失败！");
            logger.error(e.getMessage());
        }
        return res;

    }

}

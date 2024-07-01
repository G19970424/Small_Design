package cn.com.small_design.controller.base;

import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.service.common.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gejj
 * @create 2024年04月23日 15:50
 * @version 1.0
 *
 * 请求验证码接口
 *
 *
 */
@RestController
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码
     *
     * 后期需求：
     *  1.根据不同需求，请求不同时效验证码
     *  2.为服务器安全，相同ip地址，短时间内限制访问次数
     * @return
     */
    @GetMapping("/captcha")
    public RestResponse getCaptcha(){
        return ResultApi.ok(captchaService.cacheCaptcha());
    }

}
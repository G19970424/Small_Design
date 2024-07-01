package cn.com.small_design.controller.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author gejj
 * @create 2024年04月01日 15:17
 * @version 1.0
 *
 * 系统初始化接口
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "/";
    }

}

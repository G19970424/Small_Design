package cn.com.small_design.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author gejj
 * @createTime 2024年04月01日 15:17
 * @version 1.0
 */
@Controller
@RequestMapping
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(){
        return "/";
    }

}

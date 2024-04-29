package cn.com.small_design.common.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author gejj
 * @create 2024年04月23日 15:48
 * @version 1.0
 *
 * 验证码配置类
 * <p>
 *     系统采用kaptcha2.3.2 生成验证码
 * </p>
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha kaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        //图片边框
        properties.setProperty("kaptcha.border", "no");
        //边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //验证码宽度
        properties.setProperty("kaptcha.image.width", "110");
        //验证码高度
        properties.setProperty("kaptcha.image.height", "40");
        //properties.setProperty("kaptcha.textproducer.char.string","23456789abcdefghkmnpqrstuvwxyzABCDEFGHKMNPRSTUVWXYZ");
        //验证码文本大小
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //验证码文本字符间距
        properties.setProperty("kaptcha.textproducer.char.space","3");
        //properties.setProperty("kaptcha.session.key", "code");
        //验证码文本字符长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //验证码文本字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 干扰实现类
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}

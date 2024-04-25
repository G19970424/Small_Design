package cn.com.small_design.controller.base.dto;


/**
 * @author gejj
 * @create 2024年03月25日 15:48
 * @version 1.0
 *
 * 登录用户实体类
 */
public class UserDto {
    /** 登录用户名称 */
    private String username;
    /** 登录用户密码 */
    private String password;
    /** 登录验证码 */
    private String captcha;
    /** 验证码key*/
    private String captchaKey;


    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }
}

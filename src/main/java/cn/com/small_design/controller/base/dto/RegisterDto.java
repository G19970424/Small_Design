package cn.com.small_design.controller.base.dto;

/**
 * @author gejj
 * @create 2024年04月09日 16:39
 * @version 1.0
 */
public class RegisterDto {
    /** 用户名称 */
    private String username;
    /** 登录密码 */
    private String password;


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
}

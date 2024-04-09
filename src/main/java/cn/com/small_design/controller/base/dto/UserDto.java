package cn.com.small_design.controller.base.dto;


/**
 * @author gejj
 * @createTime 2024年03月25日 15:48
 * @version 1.0
 */
public class UserDto {
    /** 登录用户名称 */
    String username;
    /** 登录用户密码 */
    String password;

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
}

package cn.com.small_design.controller.base.dto;

/**
 * @author gejj
 * @createTime 2024年04月09日 16:39
 * @version 1.0
 */
public class RegisterDto {
    private String username;
    private String loginName;
    private String password;
    private char userType;

    public char getUserType() {
        return userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

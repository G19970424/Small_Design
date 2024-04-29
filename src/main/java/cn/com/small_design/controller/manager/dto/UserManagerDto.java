package cn.com.small_design.controller.manager.dto;

/**
 * @author gejj
 * @create 2024年04月29日 16:25
 * @version 1.0
 */
public class UserManagerDto {
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 登录名 */
    private String loginName;
    /** 年龄 */
    private short age;
    /** 电话 */
    private String phone;
    /** 性别 */
    private byte sex;
    /** 类型 */
    private char type;
    /** 邮箱 */
    private String email;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

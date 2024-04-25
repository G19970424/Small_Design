package cn.com.small_design.dao.dao.pojo;

import java.util.Date;

/**
 * @author gejj
 * @create 2024年03月25日 15:40
 * @version 1.0
 */
public class User {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户登录名称
     */
    private String loginName;

    /**
     *  昵称
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 性别
     */
    private char sex;

    /**
     * 年龄
     */
    private int age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 用户类型
     */
    private char userType;

    /**
     * 创建时间
     */
    private Date createTime;

    public User() {
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getUserType() {
        return userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

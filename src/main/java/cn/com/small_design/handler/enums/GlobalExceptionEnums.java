package cn.com.small_design.handler.enums;

/**
 * @author gejj
 * @create 2024年04月25日 16:21
 * @version 1.0
 */
public enum GlobalExceptionEnums {
    /** 用户登录相关异常 */
    INCORRECT_USERNAME_OR_PASSWORD("2001","用户名或密码错误"),
    REPEAT_OF_USERNAME("2002","用户名重复，请重新输入"),
    USER_LOGIN_TIMEOUT("2003","用户登录过期，请重新登录"),
    OBTAIN_USERID_ERROR("3004","用户id获取异常"),
    /** 业务相关异常 */
    CAPTCHA_GENERATION_FAIL("3001","验证码生成失败，请稍后重试"),
    CAPTCHA_ERROR("3002","验证码错误"),
    /** 数据库异常相应 */
    DATABASE_ERROR("4001","后台异常，请联系管理员处理！"),
    /** JWT */
    TOKEN_PARSING_EXCEPTION("5001","token解析异常"),
    ;


    private final String code;
    private final String message;

    GlobalExceptionEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

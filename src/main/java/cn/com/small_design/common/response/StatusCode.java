package cn.com.small_design.common.response;

/**
 * @author gejj
 * @createTime 2024年03月25日 14点59分
 * @version 1.0
 */
public enum StatusCode {
    SUCCESS("200","OK"),
    FAIL("300","FAIL"),
    BAD_REQUEST("400", "Bad Request"),
    UNAUTHORIZED("401", "Unauthorized"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    ;

    private final String code;
    private final String message;

    StatusCode(String code, String message) {
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

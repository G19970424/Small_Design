package cn.com.small_design.common.exception;

import cn.com.small_design.common.response.StatusCode;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;

/**
 * @author gejj
 * @create 2024年04月25日 16:10
 * @version 1.0
 *
 * 业务类异常处理
 */
public class BusinessException extends RuntimeException{

    private final GlobalExceptionEnums statusCode;


    public BusinessException(GlobalExceptionEnums enums) {
        super(enums.getMessage());
        this.statusCode = enums;
    }



    public GlobalExceptionEnums getStatusCode() {
        return statusCode;
    }
}

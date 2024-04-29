package cn.com.small_design.handler;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.response.RestResponse;
import cn.com.small_design.common.response.ResultApi;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 *
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public RestResponse handlerIOException(SQLException exception){
        logger.error("SQLException:{}",exception.getMessage());
        return ResultApi.fail(GlobalExceptionEnums.DATABASE_ERROR);
    }


    @ExceptionHandler(BusinessException.class)
    public RestResponse handlerBusinessException(BusinessException exception){
        logger.error("BusinessException:{}",exception.getMessage());
        return ResultApi.fail(exception.getStatusCode());
    }
}

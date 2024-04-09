package cn.com.small_design.common.response;

import java.util.Date;

/**
 * @author gejj
 * @createTime 2024年03月25日 14点59分
 * @version 1.0
 */
public class RestResponse<T> {
    /** 状态码 */
    private int code;
    /** 响应消息 */
    private String message;
    /** 响应数据 */
    private T data;
    /** 数据数量 */
    private int count;
    /** 是否成功 */
    private boolean success;
    /** 响应时间 */
    private Date dateTime;

    public RestResponse() {
        this.code = 200;
        this.success = true;
    }


    public RestResponse(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public RestResponse(int code, String message, T data,boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }



    public RestResponse(int code, String message, T data, int count, boolean success, Date dateTime) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
        this.success = success;
        this.dateTime = dateTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}

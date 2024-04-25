package cn.com.small_design.common.response;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 */
public class ResultApi {

    public static RestResponse<?> ok(){
        return new RestResponse<>(200,ResultEnum.SUCCESS.getValue(),true);
    }

    public static RestResponse<?> ok(Object data){
        return new RestResponse<>(200,ResultEnum.SUCCESS.getValue(),data,true);
    }

    public static RestResponse<?> ok(Object data,String msg){
        return  new RestResponse<>(200,msg,data,true);
    }

    public static RestResponse<?> fail(){
        return  new RestResponse<>(400,ResultEnum.FAIL.getValue(),false);
    }

    public static RestResponse<?> fail(String msg){
        return  new RestResponse<>(400,msg,false);
    }
}

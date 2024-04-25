package cn.com.small_design.common.response;

/**
 * @author gejj
 * @create 2024年03月25日 14点59分
 * @version 1.0
 */
public enum ResultEnum {
    SUCCESS("操作成功！"),
    FAIL("操作失败！");

    ResultEnum(String value){
        this.value =value;
    }

    private String value;

    public String getValue(){
        return this.value;
    }
}

package cn.com.small_design.common.response;

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

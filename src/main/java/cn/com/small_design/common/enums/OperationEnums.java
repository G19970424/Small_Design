package cn.com.small_design.common.enums;

/**
 * @author gejj
 * @create 2024年05月06日 14:02
 * @version 1.0
 *
 * 系统操作日志枚举类型
 */
public enum OperationEnums {
    MODIFY("修改"),
    DELETE("删除")
    ;

    OperationEnums(String label){
        this.label = label;
    }

    private String label;
}

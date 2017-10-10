package cn.com.scal.components.enums;

public enum  ExamineTypeEnum {
    APPLY("申请审批"), REPORT("总结审批");
    private String text;
    ExamineTypeEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

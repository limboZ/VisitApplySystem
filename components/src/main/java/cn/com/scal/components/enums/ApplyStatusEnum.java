package cn.com.scal.components.enums;

public enum ApplyStatusEnum {
    WAITING("待审批"), PROCESSING("审批中"), COMPLETE("审批通过");
    private String text;
    ApplyStatusEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

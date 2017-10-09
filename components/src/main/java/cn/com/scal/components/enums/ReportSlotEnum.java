package cn.com.scal.components.enums;

public enum ReportSlotEnum {
    MORNING("早上"), AFTERNOON("下午");
    private String text;
    ReportSlotEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package cn.com.scal.components.enums;

/**
 * Created by vslimit on 15/8/28.
 */
public enum ClassifyEnum {

    SYSTEM("系统日志"),USER("用户日志");

    private String text;
    ClassifyEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

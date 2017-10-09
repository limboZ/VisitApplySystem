package cn.com.scal.components.enums;

/**
 * Created by vslimit on 15-1-13.
 */
public enum RoleEnum {
    ADMIN("管理员"), USER("用户");

    private String text;

    private RoleEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}

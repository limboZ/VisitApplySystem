package cn.com.scal.components.enums;

/**
 * Created by limboZ on 2017/10/12.
 */
public enum ReportEnum {

    TRIP("行程"),FINAL("总结");
    private String text;
    ReportEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ReportEnum EnumFormText(String text) throws Exception {

        ReportEnum[] enums = ReportEnum.values();
        for (ReportEnum enumo : enums) {

            if(enumo.getText().equals(text)){
                return enumo;
            }
        }

        throw new Exception("类型未找到！");

    }

    public static ReportEnum EnumFormName(String name) throws Exception {

        ReportEnum[] enums = ReportEnum.values();
        for (ReportEnum enumo : enums) {

            if(enumo.name().equals(name)){
                return enumo;
            }
        }

        throw new Exception("类型未找到！");

    }
}


package cn.com.scal.components.enums;

public enum  ApplyAndReportExamineStatusEnum {
    WAITING("待审批"), COMPLETE("审批完成");
    private String text;
    ApplyAndReportExamineStatusEnum(String text){
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

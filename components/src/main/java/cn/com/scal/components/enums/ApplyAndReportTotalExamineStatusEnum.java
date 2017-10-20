package cn.com.scal.components.enums;

public enum ApplyAndReportTotalExamineStatusEnum {
    NO("初始值"), WAITING_CONFIG("待配置"), CONFIGURED_NOT_SUBMIT("配置未提交"), PROCESSING("审批中"), COMPLETE("审批通过");
    private String text;
    ApplyAndReportTotalExamineStatusEnum(String text){
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

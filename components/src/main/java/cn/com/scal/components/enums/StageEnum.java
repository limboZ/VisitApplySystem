package cn.com.scal.components.enums;

public enum StageEnum {
    DRAFT("草稿"), APPLY_EXAMINE("申请审批"), REPORT("填写总结"), REPORT_EXAMINE("总结审批"), COMPLETE("完成");
    private String text;
    StageEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static StageEnum EnumFormText(String text) throws Exception {

        StageEnum[] enums = StageEnum.values();
        for (StageEnum enumo : enums) {

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

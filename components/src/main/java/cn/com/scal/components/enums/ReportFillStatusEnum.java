package cn.com.scal.components.enums;

public enum ReportFillStatusEnum {
    NO("初始值"), UN_SUBMIT("未提交"), SUBMIT("已提交");
    private String text;
    ReportFillStatusEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ReportSlotEnum EnumFormText(String text) throws Exception {

        ReportSlotEnum[] enums = ReportSlotEnum.values();
        for (ReportSlotEnum enumo : enums) {

            if(enumo.getText().equals(text)){
                return enumo;
            }
        }

        throw new Exception("类型未找到！");

    }
}

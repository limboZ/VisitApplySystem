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

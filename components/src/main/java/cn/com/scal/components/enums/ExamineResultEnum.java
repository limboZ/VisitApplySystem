package cn.com.scal.components.enums;

public enum ExamineResultEnum {
    WAITING("待审批"), COMPLETE("审批通过");
    private String text;
    ExamineResultEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ExamineResultEnum EnumFormText(String text) throws Exception {

        ExamineResultEnum[] enums = ExamineResultEnum.values();
        for (ExamineResultEnum enumo : enums) {

            if(enumo.getText().equals(text)){
                return enumo;
            }
        }

        throw new Exception("类型未找到！");

    }
}

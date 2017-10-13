package cn.com.scal.components.enums;

public enum ExamineResultEnum {
    PASS("同意"), REFUSE("不同意");
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

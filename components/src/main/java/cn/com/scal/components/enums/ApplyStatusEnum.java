package cn.com.scal.components.enums;

public enum ApplyStatusEnum {
    DRAT("草稿"),UN_CONFIG("待配置"), CONFIGURED_NOT_SUBMIT("已配置未提交"), WAITING("待审批"), PROCESSING("审批中"), COMPLETE("审批通过");
    private String text;
    ApplyStatusEnum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ApplyStatusEnum EnumFormText(String text) throws Exception {

        ApplyStatusEnum[] enums = ApplyStatusEnum.values();
        for (ApplyStatusEnum enumo : enums) {

            if(enumo.getText().equals(text)){
                return enumo;
            }
        }

        throw new Exception("类型未找到！");

    }
}

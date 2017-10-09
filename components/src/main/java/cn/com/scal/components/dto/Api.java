package cn.com.scal.components.dto;

import cn.com.scal.components.utils.ErrorMessage;

/**
 * Created by vslimit on 15/4/30.
 */
public class Api<T> {

    public static final Integer SUCCESS_CODE = 0;


    public static final Integer ERROR_CODE = -1;
    public static final Integer NO_POWER_CODE = -90;
    public static final Integer ERROR_LOGIN_CODE = -10001;


    private Integer code = SUCCESS_CODE;
    private String tip = ErrorMessage.SUCCESS;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void init(Integer code, String tip) {
        setCode(code);
        setTip(tip);
    }
}

package cn.com.scal.components.dto.front.domain;

import java.util.Date;

public class ExamineProgress {
    private String advise;
    private String ret;
    private String examinePeopleName;
    private String result;
    private Date passTime;

    public ExamineProgress() {
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getExaminePeopleName() {
        return examinePeopleName;
    }

    public void setExaminePeopleName(String examinePeopleName) {
        this.examinePeopleName = examinePeopleName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }
}

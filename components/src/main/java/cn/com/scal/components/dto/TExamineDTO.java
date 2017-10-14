package cn.com.scal.components.dto;

import cn.com.scal.components.domain.TExamineEntity;
import cn.com.scal.components.enums.ExamineResultEnum;

import java.util.Date;
import java.sql.Timestamp;

public class TExamineDTO extends BaseDTO<TExamineDTO, TExamineEntity> {
    private Integer id;
    private String examineType;
    private String examinePeopleId;
    private String examinePeopleName;
    private String examinePeoplePost;
    private Integer order;
    private String examineResult;
    private ExamineResultEnum result;
    private String advise;
    private Date passTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;
    private Integer applyId;

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExamineType() {
        return examineType;
    }

    public void setExamineType(String examineType) {
        this.examineType = examineType;
    }

    public String getExaminePeopleId() {
        return examinePeopleId;
    }

    public void setExaminePeopleId(String examinePeopleId) {
        this.examinePeopleId = examinePeopleId;
    }

    public String getExaminePeopleName() {
        return examinePeopleName;
    }

    public void setExaminePeopleName(String examinePeopleName) {
        this.examinePeopleName = examinePeopleName;
    }

    public String getExaminePeoplePost() {
        return examinePeoplePost;
    }

    public void setExaminePeoplePost(String examinePeoplePost) {
        this.examinePeoplePost = examinePeoplePost;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    public ExamineResultEnum getResult() {
        return result;
    }

    public void setResult(ExamineResultEnum result) {
        this.result = result;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }
}

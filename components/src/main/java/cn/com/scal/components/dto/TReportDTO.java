package cn.com.scal.components.dto;

import cn.com.scal.components.domain.TReportEntity;
import cn.com.scal.components.enums.ReportSlotEnum;

import java.sql.Date;
import java.sql.Timestamp;

public class TReportDTO extends BaseDTO<TReportDTO, TReportEntity> {
    private int id;
    private Integer creatorId;
    private String content;
    private Date reportDate;
    private ReportSlotEnum reportSlot;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;
    private int applyId;

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public ReportSlotEnum getReportSlot() {
        return reportSlot;
    }

    public void setReportSlot(ReportSlotEnum reportSlot) {
        this.reportSlot = reportSlot;
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

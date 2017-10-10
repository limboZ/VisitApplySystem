package cn.com.scal.components.dto.front.domain;

import java.util.Date;

public class ApplyPreview {
    private Integer id;
    private String teamName;
    private Date applyCreateTime;
    private String applyExamineStatus;
    private String isFilledReport;
    private String reportExamineStatus;

    public ApplyPreview() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Date getApplyCreateTime() {
        return applyCreateTime;
    }

    public void setApplyCreateTime(Date applyCreateTime) {
        this.applyCreateTime = applyCreateTime;
    }

    public String getApplyExamineStatus() {
        return applyExamineStatus;
    }

    public void setApplyExamineStatus(String applyExamineStatus) {
        this.applyExamineStatus = applyExamineStatus;
    }

    public String getIsFilledReport() {
        return isFilledReport;
    }

    public void setIsFilledReport(String isFilledReport) {
        this.isFilledReport = isFilledReport;
    }

    public String getReportExamineStatus() {
        return reportExamineStatus;
    }

    public void setReportExamineStatus(String reportExamineStatus) {
        this.reportExamineStatus = reportExamineStatus;
    }
}

package cn.com.scal.components.dto.front.domain;

import java.util.Date;

public class Report {
    private Integer id;
    private String content;
    private Date reportDate;
    private String reportSlot;
    private String reportType;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Report() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReportSlot() {
        return reportSlot;
    }

    public void setReportSlot(String reportSlot) {
        this.reportSlot = reportSlot;
    }
}

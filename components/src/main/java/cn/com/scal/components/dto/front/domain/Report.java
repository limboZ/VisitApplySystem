package cn.com.scal.components.dto.front.domain;

import java.util.Date;

public class Report {
    private String content;
    private Date reportDate;
    private String reportSlot;

    public Report() {
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

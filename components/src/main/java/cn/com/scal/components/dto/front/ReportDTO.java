package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.Report;

/**
 * 用于接收前端填写的总结报告
 */
public class ReportDTO {
    private Report[] reports;

    public ReportDTO() {
    }

    public Report[] getReports() {
        return reports;
    }

    public void setReports(Report[] reports) {
        this.reports = reports;
    }
}

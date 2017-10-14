package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.Report;

/**
 * 用于接收前端填写的总结报告
 */
public class ReportDTO {
    private Integer applyId;
    private Report[] reports;

    public ReportDTO() {
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Report[] getReports() {
        return reports;
    }

    public void setReports(Report[] reports) {
        this.reports = reports;
    }
}

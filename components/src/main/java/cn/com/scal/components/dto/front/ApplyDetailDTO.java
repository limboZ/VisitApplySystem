package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.*;

import java.util.Date;

/**
 * 1、用于返回给前端出访申请详情，和前端修改后接收前端发送过来的被修改后的出访详情内容
 * 2、用户界面和管理员界面的都统一在了这个类里面
 */
public class ApplyDetailDTO {
    private Integer id;
    private String teamName;
    private String applyUserName;
    private String commissionType;
    private Date startTime;
    private Date endTime;
    private String reason;

    private Destination[] destinations;
    private TeamMate[] teamMates;

    private ExaminePeople[] applyExaminePeoples;

    private ExamineProgress[] applyExamineProgresses;

    private Report[] reports;

    private ExaminePeople[] reportExaminePeoples;

    private ExamineProgress[] reportExamineProgresses;

    public ApplyDetailDTO() {
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

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Destination[] getDestinations() {
        return destinations;
    }

    public void setDestinations(Destination[] destinations) {
        this.destinations = destinations;
    }

    public TeamMate[] getTeamMates() {
        return teamMates;
    }

    public void setTeamMates(TeamMate[] teamMates) {
        this.teamMates = teamMates;
    }

    public ExaminePeople[] getApplyExaminePeoples() {
        return applyExaminePeoples;
    }

    public void setApplyExaminePeoples(ExaminePeople[] applyExaminePeoples) {
        this.applyExaminePeoples = applyExaminePeoples;
    }

    public ExamineProgress[] getApplyExamineProgresses() {
        return applyExamineProgresses;
    }

    public void setApplyExamineProgresses(ExamineProgress[] applyExamineProgresses) {
        this.applyExamineProgresses = applyExamineProgresses;
    }

    public Report[] getReports() {
        return reports;
    }

    public void setReports(Report[] reports) {
        this.reports = reports;
    }

    public ExaminePeople[] getReportExaminePeoples() {
        return reportExaminePeoples;
    }

    public void setReportExaminePeoples(ExaminePeople[] reportExaminePeoples) {
        this.reportExaminePeoples = reportExaminePeoples;
    }

    public ExamineProgress[] getReportExamineProgresses() {
        return reportExamineProgresses;
    }

    public void setReportExamineProgresses(ExamineProgress[] reportExamineProgresses) {
        this.reportExamineProgresses = reportExamineProgresses;
    }
}

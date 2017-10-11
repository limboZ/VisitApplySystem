package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.Destination;
import cn.com.scal.components.dto.front.domain.TeamMate;

import java.util.Date;

/**
 * 用于接收前端填写的出访申请
 */
public class ApplyDTO {
    private Integer id;
    private String commissionType;
    private Date startTime;
    private Date endTime;
    private String reason;

    private Destination[] destinations;
    private TeamMate[] teamMates;

    public ApplyDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

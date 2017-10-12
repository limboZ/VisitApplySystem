package cn.com.scal.components.domain;

import cn.com.scal.components.enums.ApplyStatusEnum;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_apply", schema = "visit_apply", catalog = "")
public class TApplyEntity extends Base<TApplyEntity>{
    private int id;
    private String teamName;
    private String applyUserId;
    private String commissionType;
    private Date startTime;
    private Date endTime;
    private String reason;
    private ApplyStatusEnum applyStatus;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;

    private List<TDestinationEntity> destinationEntities;
    private List<TTeamEntity> tTeamEntities;
    private List<TReportEntity> reportEntities;
    private List<TExamineEntity> examineEntities;


    @OneToMany(mappedBy = "applyId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<TDestinationEntity> getDestinationEntities() {
        return destinationEntities;
    }

    public void setDestinationEntities(List<TDestinationEntity> destinationEntities) {
        this.destinationEntities = destinationEntities;
    }

    @OneToMany(mappedBy = "applyId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<TTeamEntity> gettTeamEntities() {
        return tTeamEntities;
    }

    public void settTeamEntities(List<TTeamEntity> tTeamEntities) {
        this.tTeamEntities = tTeamEntities;
    }

    @OneToMany(mappedBy = "applyId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<TReportEntity> getReportEntities() {
        return reportEntities;
    }

    public void setReportEntities(List<TReportEntity> reportEntities) {
        this.reportEntities = reportEntities;
    }


    @OneToMany(mappedBy = "applyId",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<TExamineEntity> getExamineEntities() {
        return examineEntities;
    }

    public void setExamineEntities(List<TExamineEntity> examineEntities) {
        this.examineEntities = examineEntities;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "team_name")
    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Basic
    @Column(name = "apply_user_id")
    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    @Basic
    @Column(name = "commission_type")
    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    @Basic
    @Column(name = "start_time")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Basic
    @Column(name = "apply_status")
    @Enumerated(EnumType.STRING)
    public ApplyStatusEnum getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(ApplyStatusEnum applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "data_mark")
    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    @Basic
    @Column(name = "f1")
    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    @Basic
    @Column(name = "f2")
    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TApplyEntity that = (TApplyEntity) o;

        if (id != that.id) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (applyUserId != null ? !applyUserId.equals(that.applyUserId) : that.applyUserId != null) return false;
        if (commissionType != null ? !commissionType.equals(that.commissionType) : that.commissionType != null)
            return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        if (applyStatus != null ? !applyStatus.equals(that.applyStatus) : that.applyStatus != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (dataMark != null ? !dataMark.equals(that.dataMark) : that.dataMark != null) return false;
        if (f1 != null ? !f1.equals(that.f1) : that.f1 != null) return false;
        if (f2 != null ? !f2.equals(that.f2) : that.f2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (applyUserId != null ? applyUserId.hashCode() : 0);
        result = 31 * result + (commissionType != null ? commissionType.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (applyStatus != null ? applyStatus.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (dataMark != null ? dataMark.hashCode() : 0);
        result = 31 * result + (f1 != null ? f1.hashCode() : 0);
        result = 31 * result + (f2 != null ? f2.hashCode() : 0);
        return result;
    }
}

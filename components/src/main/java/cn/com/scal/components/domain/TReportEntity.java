package cn.com.scal.components.domain;

import cn.com.scal.components.enums.ReportSlotEnum;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_report", schema = "visit_apply", catalog = "")
public class TReportEntity extends Base<TReportEntity>{
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

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private TApplyEntity applyId;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    public TApplyEntity getApplyId(){
        return applyId;
    }

    public void setApplyId(TApplyEntity applyId) {
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "creator_id")
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "report_date")
    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Basic
    @Column(name = "report_slot")
    @Enumerated(EnumType.STRING)
    public ReportSlotEnum getReportSlot() {
        return reportSlot;
    }

    public void setReportSlot(ReportSlotEnum reportSlot) {
        this.reportSlot = reportSlot;
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

        TReportEntity that = (TReportEntity) o;

        if (id != that.id) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (reportDate != null ? !reportDate.equals(that.reportDate) : that.reportDate != null) return false;
        if (reportSlot != null ? !reportSlot.equals(that.reportSlot) : that.reportSlot != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (dataMark != null ? !dataMark.equals(that.dataMark) : that.dataMark != null) return false;
        if (f1 != null ? !f1.equals(that.f1) : that.f1 != null) return false;
        if (f2 != null ? !f2.equals(that.f2) : that.f2 != null) return false;
        return applyId != null ? applyId.equals(that.applyId) : that.applyId == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (reportDate != null ? reportDate.hashCode() : 0);
        result = 31 * result + (reportSlot != null ? reportSlot.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (dataMark != null ? dataMark.hashCode() : 0);
        result = 31 * result + (f1 != null ? f1.hashCode() : 0);
        result = 31 * result + (f2 != null ? f2.hashCode() : 0);
        result = 31 * result + (applyId != null ? applyId.hashCode() : 0);
        return result;
    }
}

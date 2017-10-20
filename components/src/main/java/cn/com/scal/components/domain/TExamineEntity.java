package cn.com.scal.components.domain;

import cn.com.scal.components.enums.ApplyAndReportExamineStatusEnum;
import cn.com.scal.components.enums.StageEnum;
import cn.com.scal.components.enums.ExamineResultEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_examine", schema = "visit_apply", catalog = "")
public class TExamineEntity extends Base<TExamineEntity>{
    private Integer id;
    private ExamineTypeEnum examineType;
    private String examinePeopleId;
    private String examinePeopleName;
    private String examinePeoplePost;
    private Integer orders;
    private ExamineResultEnum examineResult;    // 领导审批的结果（同意， 不同意）
    private ApplyAndReportExamineStatusEnum status;     // 审批的状态（待审批，审批通过）
    private String advise;
    private Date passTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private TApplyEntity applyId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    public TApplyEntity getApplyId(){
        return applyId;
    }

    public void setApplyId(TApplyEntity applyId) {
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "examine_type")
    @Enumerated(EnumType.STRING)
    public ExamineTypeEnum getExamineType() {
        return examineType;
    }

    public void setExamineType(ExamineTypeEnum examineType) {
        this.examineType = examineType;
    }

    @Basic
    @Column(name = "examine_people_id")
    public String getExaminePeopleId() {
        return examinePeopleId;
    }

    public void setExaminePeopleId(String examinePeopleId) {
        this.examinePeopleId = examinePeopleId;
    }

    @Basic
    @Column(name = "examine_people_name")
    public String getExaminePeopleName() {
        return examinePeopleName;
    }

    public void setExaminePeopleName(String examinePeopleName) {
        this.examinePeopleName = examinePeopleName;
    }

    @Basic
    @Column(name = "examine_people_post")
    public String getExaminePeoplePost() {
        return examinePeoplePost;
    }

    public void setExaminePeoplePost(String examinePeoplePost) {
        this.examinePeoplePost = examinePeoplePost;
    }

    @Basic
    @Column(name = "orders")
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Basic
    @Column(name = "examine_result")
    @Enumerated(EnumType.STRING)
    public ExamineResultEnum getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(ExamineResultEnum examineResult) {
        this.examineResult = examineResult;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public ApplyAndReportExamineStatusEnum getStatus() {
        return status;
    }

    public void setStatus(ApplyAndReportExamineStatusEnum status) {
        this.status = status;
    }


    @Basic
    @Column(name = "advise")
    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise;
    }

    @Basic
    @Column(name = "pass_time")
    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    @Basic
    @Column(name = "create_time", updatable = false)
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
}

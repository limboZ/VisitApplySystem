package cn.com.scal.components.domain;

import cn.com.scal.components.enums.ExamineResultEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_examine", schema = "visit_apply", catalog = "")
public class TExamineEntity extends Base<TExamineEntity>{
    private int id;
    private ExamineTypeEnum examineType;
    private String examinePeopleId;
    private String examinePeopleName;
    private String examinePeoplePost;
    private Integer orders;
    private String examineResult;
    private ExamineResultEnum result;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }

    @Basic
    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    public ExamineResultEnum getResult() {
        return result;
    }

    public void setResult(ExamineResultEnum result) {
        this.result = result;
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

        TExamineEntity that = (TExamineEntity) o;

        if (id != that.id) return false;
        if (examineType != that.examineType) return false;
        if (examinePeopleId != null ? !examinePeopleId.equals(that.examinePeopleId) : that.examinePeopleId != null)
            return false;
        if (examinePeopleName != null ? !examinePeopleName.equals(that.examinePeopleName) : that.examinePeopleName != null)
            return false;
        if (examinePeoplePost != null ? !examinePeoplePost.equals(that.examinePeoplePost) : that.examinePeoplePost != null)
            return false;
        if (orders != null ? !orders.equals(that.orders) : that.orders != null) return false;
        if (examineResult != null ? !examineResult.equals(that.examineResult) : that.examineResult != null)
            return false;
        if (result != that.result) return false;
        if (advise != null ? !advise.equals(that.advise) : that.advise != null) return false;
        if (passTime != null ? !passTime.equals(that.passTime) : that.passTime != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (dataMark != null ? !dataMark.equals(that.dataMark) : that.dataMark != null) return false;
        if (f1 != null ? !f1.equals(that.f1) : that.f1 != null) return false;
        if (f2 != null ? !f2.equals(that.f2) : that.f2 != null) return false;
        return applyId != null ? applyId.equals(that.applyId) : that.applyId == null;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (examineType != null ? examineType.hashCode() : 0);
        result1 = 31 * result1 + (examinePeopleId != null ? examinePeopleId.hashCode() : 0);
        result1 = 31 * result1 + (examinePeopleName != null ? examinePeopleName.hashCode() : 0);
        result1 = 31 * result1 + (examinePeoplePost != null ? examinePeoplePost.hashCode() : 0);
        result1 = 31 * result1 + (orders != null ? orders.hashCode() : 0);
        result1 = 31 * result1 + (examineResult != null ? examineResult.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (advise != null ? advise.hashCode() : 0);
        result1 = 31 * result1 + (passTime != null ? passTime.hashCode() : 0);
        result1 = 31 * result1 + (createTime != null ? createTime.hashCode() : 0);
        result1 = 31 * result1 + (updateTime != null ? updateTime.hashCode() : 0);
        result1 = 31 * result1 + (dataMark != null ? dataMark.hashCode() : 0);
        result1 = 31 * result1 + (f1 != null ? f1.hashCode() : 0);
        result1 = 31 * result1 + (f2 != null ? f2.hashCode() : 0);
        result1 = 31 * result1 + (applyId != null ? applyId.hashCode() : 0);
        return result1;
    }
}

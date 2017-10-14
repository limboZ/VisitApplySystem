package cn.com.scal.components.domain;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_team", schema = "visit_apply", catalog = "")
public class TTeamEntity extends Base<TTeamEntity>{
    private int id;
    private String employeeId;
    private String employeeName;
    private String employeeDept;
    private String employeePost;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;
    private Integer orders;

    public TTeamEntity() {
    }

    public TTeamEntity(int id, Integer orders, String employeeId, String employeeName, String employeeDept, String employeePost, Timestamp createTime, Timestamp updateTime, String dataMark, TApplyEntity applyId) {
        this.id = id;
        this.orders = orders;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeDept = employeeDept;
        this.employeePost = employeePost;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.dataMark = dataMark;
        this.applyId = applyId;
    }

    @Basic
    @Column(name = "orders")
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
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
    @Column(name = "employee_id")
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Basic
    @Column(name = "employee_name")
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "employee_dept")
    public String getEmployeeDept() {
        return employeeDept;
    }

    public void setEmployeeDept(String employeeDept) {
        this.employeeDept = employeeDept;
    }

    @Basic
    @Column(name = "employee_post")
    public String getEmployeePost() {
        return employeePost;
    }

    public void setEmployeePost(String employeePost) {
        this.employeePost = employeePost;
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

        TTeamEntity that = (TTeamEntity) o;

        if (id != that.id) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (employeeName != null ? !employeeName.equals(that.employeeName) : that.employeeName != null) return false;
        if (employeeDept != null ? !employeeDept.equals(that.employeeDept) : that.employeeDept != null) return false;
        if (employeePost != null ? !employeePost.equals(that.employeePost) : that.employeePost != null) return false;
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
        result = 31 * result + (employeeId != null ? employeeId.hashCode() : 0);
        result = 31 * result + (employeeName != null ? employeeName.hashCode() : 0);
        result = 31 * result + (employeeDept != null ? employeeDept.hashCode() : 0);
        result = 31 * result + (employeePost != null ? employeePost.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (dataMark != null ? dataMark.hashCode() : 0);
        result = 31 * result + (f1 != null ? f1.hashCode() : 0);
        result = 31 * result + (f2 != null ? f2.hashCode() : 0);
        result = 31 * result + (applyId != null ? applyId.hashCode() : 0);
        return result;
    }
}

package cn.com.scal.components.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_commission_type_enum", schema = "visit_apply", catalog = "")
@Where(clause = "data_mark='1'")
public class TCommissionTypeEnumEntity extends Base<TCommissionTypeEnumEntity>{
    private Integer id;
    private String commissionType;
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

    @Basic
    @Column(name = "commission_type")
    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCommissionTypeEnumEntity that = (TCommissionTypeEnumEntity) o;

        if (id != that.id) return false;
        if (commissionType != null ? !commissionType.equals(that.commissionType) : that.commissionType != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (dataMark != null ? !dataMark.equals(that.dataMark) : that.dataMark != null) return false;
        if (f1 != null ? !f1.equals(that.f1) : that.f1 != null) return false;
        return f2 != null ? f2.equals(that.f2) : that.f2 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (commissionType != null ? commissionType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (dataMark != null ? dataMark.hashCode() : 0);
        result = 31 * result + (f1 != null ? f1.hashCode() : 0);
        result = 31 * result + (f2 != null ? f2.hashCode() : 0);
        return result;
    }
}

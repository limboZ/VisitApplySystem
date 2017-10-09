package cn.com.scal.components.dto;

import cn.com.scal.components.domain.TCommissionTypeEnumEntity;

import java.sql.Timestamp;

public class TCommissionTypeEnumDTO extends BaseDTO<TCommissionTypeEnumDTO, TCommissionTypeEnumEntity> {
    private int id;
    private String commissionType;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String dataMark;
    private String f1;
    private String f2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }
}

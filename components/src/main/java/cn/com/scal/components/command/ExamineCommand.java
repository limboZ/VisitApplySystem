package cn.com.scal.components.command;

import cn.com.scal.components.domain.TExamineEntity;
import cn.com.scal.components.dto.TExamineDTO;
import cn.com.scal.components.utils.StringUtil;

public class ExamineCommand extends BaseCommand<TExamineEntity, TExamineDTO>{
    private Integer applyId;
    private String dataMark;
    private Integer orders;
    private String examineType;

    public ExamineCommand() {
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getExamineType() {
        return examineType;
    }

    public void setExamineType(String examineType) {
        this.examineType = examineType;
    }

    public String conditions() {
        StringBuffer queryHql = new StringBuffer();
        queryMap.clear();
        if(null != applyId){
            queryHql.append(" and ").append("apply_id = :applyId");
            queryMap.put("applyId", applyId);
        }
        if(!StringUtil.isEmpty(dataMark)){
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }
        if(null != orders){
            queryHql.append(" and ").append("orders = :orders");
            queryMap.put("orders", orders);
        }
        if(!StringUtil.isEmpty(examineType)){
            queryHql.append(" and ").append("examine_type = :examineType");
            queryMap.put("examineType", examineType);
        }
        return queryHql.toString();
    }
}

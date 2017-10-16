package cn.com.scal.components.command;

import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.utils.StringUtil;

public class ApplyCommand extends BaseCommand<TApplyEntity, TApplyDTO>{
    private String applyUserId;
    private Integer applyId;
    private String applyStatus;
    private String dataMark;

    public ApplyCommand() {
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getDataMark() {
        return dataMark;
    }

    public void setDataMark(String dataMark) {
        this.dataMark = dataMark;
    }

    public String conditions() {
        StringBuffer queryHql = new StringBuffer();
        queryMap.clear();
        if(!StringUtil.isEmpty(applyUserId)){
            queryHql.append(" and ").append("apply_user_id = :applyUserId");
            queryMap.put("applyUserId", applyUserId);
        }
        if(applyId != null){
            queryHql.append(" and ").append("id = :applyId");
            queryMap.put("applyId", applyId);
        }
        if(!StringUtil.isEmpty(applyStatus)){
            queryHql.append(" and ").append("apply_status != :applyStatus");
            queryMap.put("applyStatus", applyStatus);
        }
        if(!StringUtil.isEmpty(dataMark)){
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }
        return queryHql.toString();
    }
}

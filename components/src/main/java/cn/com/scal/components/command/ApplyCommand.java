package cn.com.scal.components.command;

import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.utils.StringUtil;

public class ApplyCommand extends BaseCommand<TApplyEntity, TApplyDTO>{
    private String applyUserId;
    private String stage;
    private String dataMark;

    public ApplyCommand() {
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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
        if(!StringUtil.isEmpty(stage)){
            queryHql.append(" and ").append("apply_status != :stage");
            queryMap.put("stage", stage);
        }
        if(!StringUtil.isEmpty(dataMark)){
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }
        return queryHql.toString();
    }
}

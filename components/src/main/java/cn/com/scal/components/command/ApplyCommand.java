package cn.com.scal.components.command;

import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.utils.StringUtil;

public class ApplyCommand extends BaseCommand<TApplyEntity, TApplyDTO>{
    private Integer applyId;
    private String dataMark;

    public ApplyCommand() {
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

    public String conditions() {
        StringBuffer queryHql = new StringBuffer();
        queryMap.clear();
        if(applyId != null){
            queryHql.append(" and ").append("id = :applyId");
            queryMap.put("applyId", applyId);
        }
        if(!StringUtil.isEmpty(dataMark)){
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }
        return queryHql.toString();
    }
}

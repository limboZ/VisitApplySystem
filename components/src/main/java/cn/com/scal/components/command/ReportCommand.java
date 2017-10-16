package cn.com.scal.components.command;

import cn.com.scal.components.domain.TReportEntity;
import cn.com.scal.components.dto.TReportDTO;
import cn.com.scal.components.utils.StringUtil;

public class ReportCommand extends BaseCommand<TReportEntity, TReportDTO> {
    private Integer applyId;
    private String dataMark;

    public ReportCommand() {
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
        if (applyId != null) {
            queryHql.append(" and ").append("id = :applyId");
            queryMap.put("applyId", applyId);
        }
        if (!StringUtil.isEmpty(dataMark)) {
            queryHql.append(" and ").append("data_mark = :dataMark");
            queryMap.put("dataMark", dataMark);
        }
        return queryHql.toString();
    }
}

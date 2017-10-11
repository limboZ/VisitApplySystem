package cn.com.scal.components.service;

import cn.com.scal.components.domain.TReportEntity;
import cn.com.scal.components.dto.TReportDTO;
import cn.com.scal.components.dto.front.ReportDTO;

public interface IReportService {
    /**
     * 根据申请的主键查询出这份申请的报告
     * @param applyId   申请的主键
     * @return  对前端展示的实体
     */
    ReportDTO getReports(Integer applyId);

    /**
     * 将一个新的report存入数据库
     * @param reports   新的report的前端展示实体
     */
    void setReports(ReportDTO reports);
}

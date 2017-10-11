package cn.com.scal.components.service.impl;

import cn.com.scal.components.dto.front.ReportDTO;
import cn.com.scal.components.service.IReportService;
import org.springframework.stereotype.Service;

@Service("reportService")
public class ReportServiceImpl implements IReportService {
    @Override
    public ReportDTO getReports(Integer applyId) {
        return null;
    }

    @Override
    public void setReports(ReportDTO reports) {

    }
}

package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.IReportDao;
import cn.com.scal.components.service.IReportService;
import cn.com.scal.components.utils.DTFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service("reportService")
public class ReportServiceImpl implements IReportService {
    @Resource(name = "reportDao")
    IReportDao reportDao;

    @Override
    public void delete(int applyId, Timestamp currentTime) {
        String timestamp = DTFormatUtil.convertTimestampToStr(currentTime);
        String sql = String.format("update t_report set data_mark='0' where apply_id='%s' and update_time < '%s'", applyId, timestamp);
        reportDao.delete(sql);
    }
}

package cn.com.scal.components.service;

import java.sql.Timestamp;

public interface IReportService {
    void delete(int applyId, Timestamp currentTime);
}

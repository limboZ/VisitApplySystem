package cn.com.scal.components.service;

import java.sql.Timestamp;

public interface IExamineService {
    void delete(int applyId, Timestamp currentTime, String examineType);
}

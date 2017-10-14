package cn.com.scal.components.service;

import java.sql.Timestamp;

public interface IDestinationService {
    void delete(int applyId, Timestamp currentTime);
}

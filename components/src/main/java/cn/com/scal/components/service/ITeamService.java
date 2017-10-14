package cn.com.scal.components.service;

import java.sql.Timestamp;

public interface ITeamService {
    void delete(int applyId, Timestamp currentTime);
}

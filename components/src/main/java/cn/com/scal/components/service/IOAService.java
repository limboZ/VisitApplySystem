package cn.com.scal.components.service;

import cn.com.scal.components.dto.front.domain.TeamMate;

public interface IOAService {
    TeamMate getInfoFromOA(String OANum);
}

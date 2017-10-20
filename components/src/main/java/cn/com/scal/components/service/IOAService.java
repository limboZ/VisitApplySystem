package cn.com.scal.components.service;

import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.webservice.WFInterfaceEntity;

public interface IOAService {
    TeamMate getInfoFromOA(String OANum);
    boolean createOAItem(WFInterfaceEntity entity);
}

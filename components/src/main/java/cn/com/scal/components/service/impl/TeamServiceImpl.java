package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.ITeamDao;
import cn.com.scal.components.service.ITeamService;
import cn.com.scal.components.utils.DTFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service("teamService")
public class TeamServiceImpl implements ITeamService {
    @Resource(name = "teamDao")
    ITeamDao teamDao;

    @Override
    public void delete(int applyId, Timestamp currentTime) {
        String timestamp = DTFormatUtil.convertTimestampToStr(currentTime);
        String sql = String.format("update t_team set data_mark='0' where apply_id='%s' and update_time < '%s'", applyId, timestamp);
        teamDao.delete(sql);
    }
}

package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.IDestinationDao;
import cn.com.scal.components.service.IDestinationService;
import cn.com.scal.components.utils.DTFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service("destinationService")
public class DestinationServiceImpl implements IDestinationService {
    @Resource(name = "destinationDao")
    IDestinationDao destinationDao;

    @Override
    public void delete(int applyId, Timestamp currentTime) {
        String timestamp = DTFormatUtil.convertTimestampToStr(currentTime);
        String sql = String.format("update t_destination set data_mark='0' where apply_id='%s' and update_time < '%s'", applyId, timestamp);
        destinationDao.delete(sql);
    }
}

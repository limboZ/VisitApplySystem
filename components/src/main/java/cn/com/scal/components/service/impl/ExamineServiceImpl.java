package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.IExamineDao;
import cn.com.scal.components.service.IExamineService;
import cn.com.scal.components.utils.DTFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service("examineService")
public class ExamineServiceImpl implements IExamineService {
    @Resource(name = "examineDao")
    IExamineDao examineDao;

    @Override
    public void delete(int applyId, Timestamp currentTime) {
        String timestamp = DTFormatUtil.convertTimestampToStr(currentTime);
        String sql = String.format("update t_examine set data_mark='0' where apply_id='%s' and update_time < '%s'", applyId, timestamp);
        examineDao.delete(sql);
    }
}

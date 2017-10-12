package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.IOADao;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.service.IOAService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service("OAService")
public class OAServiceImpl implements IOAService {
    @Resource(name = "OADao")
    IOADao oaDao;

    @Override
    public TeamMate getInfoFromOA(String OANum) {
        TeamMate teamMate = null;
        String sql = String.format("SELECT t1.Employee_ID, t1.Name, t1.Rank, t2.Dept_Name\n" +
                "FROM OA_Employees t1, OA_DEFINITION_DEPT t2\n" +
                "WHERE t1.Employee_ID = '%s' and t1.Dept_ID = t2.Dept_ID", OANum);
        List<Object[]> objects = oaDao.queryBySql(sql);
        if(objects.size() == 0){
            return teamMate;
        }else {
            teamMate = new TeamMate(null, objects.get(0)[0].toString(), objects.get(0)[1].toString(), objects.get(0)[2].toString(), objects.get(0)[3].toString());
        }
        return teamMate;
    }
}

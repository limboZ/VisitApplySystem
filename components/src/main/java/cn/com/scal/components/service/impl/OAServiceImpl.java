package cn.com.scal.components.service.impl;

import cn.com.scal.components.dao.IOADao;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.service.IOAService;
import cn.com.scal.components.utils.DTFormatUtil;
import cn.com.scal.components.utils.webservice.CxfUtils;
import cn.com.scal.components.webservice.OaWfWebServiceSoap;
import cn.com.scal.components.webservice.WFInterfaceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Transactional
@Service("OAService")
public class OAServiceImpl implements IOAService {
    @Resource(name = "OADao")
    IOADao oaDao;

    @Value("${ADDRESS}")
    private String address;

    @Value("${PROJECT_CODE}")
    private String projectCode;

    @Value("${USER_NAME}")
    private String userName;

    @Value("${PASSWORD}")
    private String password;

    @Value("{INSTANCE_ID}")
    private String instanceId;

    @Value("{PROJECT_NAME}")
    private String projectName;

    @Value("{STEP_NAME}")
    private String stepName;

    @Value("{FLOW_TYPE}")
    private String flowType;

    @Value("{IMPORT_LEVEL}")
    private String importLevel;

    /**
     * 从oa数据库里面获取某个用户的详细信息
     *
     * @param OANum
     * @return
     */
    @Override
    public TeamMate getInfoFromOA(String OANum) {
        TeamMate teamMate = null;
        String sql = String.format("SELECT t1.Employee_ID, t1.Name, t2.Dept_Name, t1.Rank\n" +
                "FROM OA_Employees t1, OA_DEFINITION_DEPT t2\n" +
                "WHERE t1.Employee_ID = '%s' and t1.Dept_ID = t2.Dept_ID", OANum);
        List<Object[]> objects = oaDao.queryBySql(sql);
        if (objects.size() == 0) {
            return teamMate;
        } else {
            teamMate = new TeamMate(null, objects.get(0)[0].toString(), objects.get(0)[1].toString(), objects.get(0)[2].toString(), objects.get(0)[3].toString());
        }
        return teamMate;
    }

    /**
     * 调用oa接口把一条代办事项添加到某个员工代办里面
     *
     * @param entity
     * @return
     */
    @Override
    public boolean createOAItem(WFInterfaceEntity entity) {
        // 设置一些公共的属性
        entity.setProjectCode(this.projectCode);
        entity.setProjectName(this.projectName);
        entity.setStepName(this.stepName);
        entity.setFlowType(this.flowType);
        entity.setImportLevel(this.importLevel);
        entity.setCreatTime(DTFormatUtil.convertStrBySdf(new Date(), DTFormatUtil.SDF_ALL));
        OaWfWebServiceSoap oaWfWebServiceSoap = (OaWfWebServiceSoap) CxfUtils.createInterface(address, OaWfWebServiceSoap.class);
        String s = oaWfWebServiceSoap.insertNewWFInstanceByEntity(projectCode, userName, password, entity);
        return "1".equals(s);
    }

    /**
     * 调用OA接口把一条待办变成已办事项
     * @param examineId
     * @return
     */
    @Override
    public boolean completeOAItem(String examineId) {
        OaWfWebServiceSoap oaWfWebServiceSoap = (OaWfWebServiceSoap) CxfUtils.createInterface(address, OaWfWebServiceSoap.class);
        String s = oaWfWebServiceSoap.updateWFInstanceStatus(this.projectCode,
                this.userName,
                this.password,
                examineId,
                examineId,
                "", "",
                DTFormatUtil.convertStrBySdf(new Date(), DTFormatUtil.SDF_ALL),
                DTFormatUtil.convertStrBySdf(new Date(), DTFormatUtil.SDF_ALL));
        return "1".equals(s);
    }
}

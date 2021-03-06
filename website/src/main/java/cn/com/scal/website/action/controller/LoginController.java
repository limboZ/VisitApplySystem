package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.AdministratorCommand;
import cn.com.scal.components.domain.CurrentUser;
import cn.com.scal.components.domain.TAdministratorEntity;
import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TAdministratorDTO;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.service.IOAService;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.Consts;
import cn.com.scal.components.utils.ErrorMessage;
import cn.com.scal.components.utils.StringUtil;
import cn.com.scal.components.utils.webservice.PortalLoginUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by vslimit on 15/9/18.
 */
@Controller
public class LoginController extends AbstractController{

    private final String USER_LIST = "/user/list";
    private final String MANAGER_LIST = "/manager/list";

    @Resource(name = "OAService")
    private IOAService oaService;

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TAdministratorEntity, TAdministratorDTO, Integer> administratorService;


    /**
     * 根据从OA链接传过来的工号，从oa数据库中将这个员工的详细信息查询出来，并且将这个人的信息加入到session中，表示这个人已经登陆
     * @param employeeNum
     * @param fromSystem
     * @param session
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    @RequestMapping("/login/{employeeNum}/{fromSystem}")
    public String login(@PathVariable String employeeNum, @PathVariable String fromSystem, HttpSession session) throws IOException, DocumentException {
        if(employeeNum != null){
            AdministratorCommand administratorCommand = new AdministratorCommand();
            administratorCommand.setDataMark("1");
            administratorCommand.setEmployeeNum(employeeNum);
            List<TAdministratorEntity> tAdministratorEntities = administratorService.query(administratorCommand);
            if(0 == tAdministratorEntities.size()){
                TeamMate infoFromOA = oaService.getInfoFromOA(employeeNum);
                CurrentUser user = new CurrentUser();
                user.setEmpNo(employeeNum);
                user.setUserName(infoFromOA.getEmployeeName());
                user.setDeptName(infoFromOA.getEmployeeDept());
                session.setAttribute("currentUser", user);
                return "redirect:" + USER_LIST;
            }else {
                TAdministratorEntity entity = tAdministratorEntities.get(0);
                CurrentUser user = new CurrentUser();
                user.setEmpNo(entity.getEmployeeNum());
                user.setUserName(entity.getName());
                user.setDeptName(entity.getDept());
                session.setAttribute("currentUser", user);
                return "redirect:" + MANAGER_LIST;
            }
        }
        return null;
    }
}

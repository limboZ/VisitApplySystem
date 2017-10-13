package cn.com.scal.website.action.controller;

import cn.com.scal.components.domain.CurrentUser;
import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.domain.TDestinationEntity;
import cn.com.scal.components.domain.TTeamEntity;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.domain.Destination;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.enums.ApplyStatusEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TApplyEntity, TApplyDTO, Integer> applyService;

    //jsp页面路径定义区
    private final String LIST = "/user/list";
    private final String ADD = "/user/add";
    private final String SHOW = "/user/show";
    private final String EDIT = "/user/edit";

    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model) throws Exception {
        return LIST;
    }

    @RequestMapping("/add")
    public String add(Model model) throws Exception {
        return ADD;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> create(@RequestBody ApplyDTO applyDTO, CurrentUser user, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
        try {
            TApplyEntity tApplyEntity = new TApplyEntity();

            // 拼接出访的团队名字:首个团员的部门名称+首个团员名字+等x人赴+所有目的地国家(多个国家以、隔开)+任务类型+出访申请 例如:信息服务部冯涛等2人赴美国、加拿大国际会议出访申请
            String teamName = "" + applyDTO.getTeamMates()[0].getEmployeeDept() + applyDTO.getTeamMates()[0].getEmployeeName() + "等" + applyDTO.getTeamMates().length + "人赴";
            if (applyDTO.getDestinations().length == 1) {
                teamName = teamName + applyDTO.getDestinations()[0].getNation();
            } else if (applyDTO.getDestinations().length == 2) {
                teamName = teamName + applyDTO.getDestinations()[0].getNation() + "、" + applyDTO.getDestinations()[0].getNation();
            } else if (applyDTO.getDestinations().length == 2) {
                teamName = teamName + applyDTO.getDestinations()[0].getNation() + "、" + applyDTO.getDestinations()[1].getNation() + "、" + applyDTO.getDestinations()[2].getNation();
            }
            teamName = teamName + applyDTO.getCommissionType() + "出访申请";

            tApplyEntity.setTeamName(teamName);
            tApplyEntity.setApplyUserId(user.getEmpNo());
            tApplyEntity.setCommissionType(applyDTO.getCommissionType());
            tApplyEntity.setStartTime(applyDTO.getStartTime());
            tApplyEntity.setEndTime(applyDTO.getEndTime());
            tApplyEntity.setReason(applyDTO.getReason());
            tApplyEntity.setApplyStatus(ApplyStatusEnum.WAITING);
            tApplyEntity.setCreateTime(DateUtil.getCurrentTime());
            tApplyEntity.setUpdateTime(DateUtil.getCurrentTime());
            tApplyEntity.setDataMark("1");

            ArrayList<TDestinationEntity> tDestinationEntities = new ArrayList<>();
            for (int i = 0; i < applyDTO.getDestinations().length; i++) {
                Destination destination = applyDTO.getDestinations()[i];
                tDestinationEntities.add(new TDestinationEntity(i, destination.getNation(), destination.getDestination(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
            }
            ArrayList<TTeamEntity> tTeamEntities = new ArrayList<>();
            for (int i = 0; i < applyDTO.getTeamMates().length; i++) {
                TeamMate teamMate = applyDTO.getTeamMates()[i];
                tTeamEntities.add(new TTeamEntity(i, teamMate.getEmployeeId(), teamMate.getEmployeeName(), teamMate.getEmployeeDept(), teamMate.getEmployeePost(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
            }


            tApplyEntity.setDestinationEntities(tDestinationEntities);
            tApplyEntity.settTeamEntities(tTeamEntities);

            applyService.create(tApplyEntity);
        } catch (OtherException e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request, Model model) throws Exception {
        return SHOW;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, Model model) throws Exception {
        return EDIT;
    }

    @RequestMapping(value = "/submitEdit", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitEdit(@RequestBody ApplyDTO applyDTO, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
        TApplyEntity tApplyEntity = new TApplyEntity();

        return api;
    }
}

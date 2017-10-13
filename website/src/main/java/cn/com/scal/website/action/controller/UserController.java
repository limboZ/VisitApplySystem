package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ApplyCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.domain.Destination;
import cn.com.scal.components.dto.front.domain.ExamineProgress;
import cn.com.scal.components.dto.front.domain.Report;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.enums.ApplyStatusEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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
    public Api<Object> create(@RequestBody ApplyDTO applyDTO, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
        CurrentUser user = (CurrentUser)session.getAttribute("currentUser");
        try {
            TApplyEntity tApplyEntity = setApplyInfo(applyDTO, user);

            applyService.create(tApplyEntity);
        } catch (OtherException e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    @RequestMapping("/show/{applyId}")
    public String show(@PathVariable Integer applyId, HttpServletRequest request, Model model) throws Exception {
        ApplyDetailDTO applyDetailDTO = new ApplyDetailDTO();

        ApplyCommand applyCommand = new ApplyCommand();
        applyCommand.setApplyId(applyId);
        applyCommand.setDataMark("1");
        List<TApplyEntity> applyEntities = applyService.query(applyCommand);

        for(TApplyEntity entity: applyEntities){
            applyDetailDTO.setId(entity.getId());
            applyDetailDTO.setTeamName(entity.getTeamName());
            applyDetailDTO.setApplyUserName(entity.getApplyUserName());
            applyDetailDTO.setCommissionType(entity.getCommissionType());
            applyDetailDTO.setStartTime(entity.getStartTime());
            applyDetailDTO.setEndTime(entity.getEndTime());
            applyDetailDTO.setReason(entity.getReason());

            // 将目的地和队员信息取出
            Destination[] destinations = new Destination[entity.getDestinationEntities().size()];
            for(int i = 0; i < entity.getDestinationEntities().size(); i ++){
                TDestinationEntity tDestinationEntity = entity.getDestinationEntities().get(i);
                Destination destination = new Destination();

                destination.setId(tDestinationEntity.getId());
                destination.setDestination(tDestinationEntity.getDestination());
                destination.setNation(tDestinationEntity.getNation());

                destinations[i] = destination;
            }
            TeamMate[] teamMates = new TeamMate[entity.gettTeamEntities().size()];
            for(int i = 0; i < entity.gettTeamEntities().size(); i++){
                TTeamEntity tTeamEntity = entity.gettTeamEntities().get(i);
                TeamMate teamMate = new TeamMate();
                teamMate.setId(tTeamEntity.getId());
                teamMate.setEmployeeId(tTeamEntity.getEmployeeId());
                teamMate.setEmployeeName(tTeamEntity.getEmployeeName());
                teamMate.setEmployeeDept(tTeamEntity.getEmployeeDept());
                teamMate.setEmployeeDept(tTeamEntity.getEmployeePost());

                teamMates[i] = teamMate;
            }

            ArrayList<ExamineProgress> applyExamineProgresses = new ArrayList<>();
            ArrayList<ExamineProgress> reportExamineProgresses = new ArrayList<>();
            for(int i = 0; i < entity.getExamineEntities().size(); i++){
                TExamineEntity examineEntity = entity.getExamineEntities().get(i);
                if(examineEntity.getExamineType().name().equals(ExamineTypeEnum.APPLY.name())){
                    ExamineProgress progress = new ExamineProgress();
                    progress.setId(examineEntity.getId());
                    progress.setAdvise(examineEntity.getAdvise());
                    progress.setExaminePeopleName(examineEntity.getExaminePeopleName());
                    progress.setPassTime(examineEntity.getPassTime());
                    progress.setRet(examineEntity.getResult().name());
                    progress.setResult(examineEntity.getExamineResult().name());

                    applyExamineProgresses.add(progress);
                }else if(examineEntity.getExamineType().name().equals(ExamineTypeEnum.REPORT.name())){
                    ExamineProgress progress = new ExamineProgress();
                    progress.setId(examineEntity.getId());
                    progress.setAdvise(examineEntity.getAdvise());
                    progress.setExaminePeopleName(examineEntity.getExaminePeopleName());
                    progress.setPassTime(examineEntity.getPassTime());
                    progress.setRet(examineEntity.getResult().name());
                    progress.setResult(examineEntity.getExamineResult().name());

                    reportExamineProgresses.add(progress);
                }
            }

            Report[] reports = new Report[entity.getReportEntities().size()];
            for(int i = 0; i < entity.getReportEntities().size(); i++){
                TReportEntity tReportEntity = entity.getReportEntities().get(i);
                Report report = new Report();
                report.setId(tReportEntity.getId());
                report.setContent(tReportEntity.getContent());
                report.setReportDate(tReportEntity.getReportDate());
                report.setReportSlot(tReportEntity.getReportSlot().name());
                report.setReportType(tReportEntity.getReportType().name());

                reports[i] = report;
            }


            applyDetailDTO.setDestinations(destinations);
            applyDetailDTO.setTeamMates(teamMates);
            applyDetailDTO.setApplyExamineProgresses(applyExamineProgresses.toArray(new ExamineProgress[applyExamineProgresses.size()]));
            applyDetailDTO.setApplyExamineProgresses(reportExamineProgresses.toArray(new ExamineProgress[reportExamineProgresses.size()]));
            applyDetailDTO.setReports(reports);
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return SHOW;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, Model model) throws Exception {
        return EDIT;
    }

    @RequestMapping(value = "/submitEdit", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitEdit(@RequestBody ApplyDTO applyDTO, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
        CurrentUser user = (CurrentUser)session.getAttribute("currentUser");
        try {
            TApplyEntity tApplyEntity = setApplyInfo(applyDTO, user);
            applyService.createOrUpdate(tApplyEntity);
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    private TApplyEntity setApplyInfo(@RequestBody ApplyDTO applyDTO, CurrentUser user) {
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
        tApplyEntity.setApplyUserName(user.getUserName());
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
        return tApplyEntity;
    }
}

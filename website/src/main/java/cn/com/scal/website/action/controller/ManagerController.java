package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ApplyCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.domain.*;
import cn.com.scal.components.enums.ApplyStatusEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;
import cn.com.scal.components.enums.ReportEnum;
import cn.com.scal.components.enums.ReportSlotEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.IDestinationService;
import cn.com.scal.components.service.IExamineService;
import cn.com.scal.components.service.IReportService;
import cn.com.scal.components.service.ITeamService;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.DTFormatUtil;
import cn.com.scal.components.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TApplyEntity, TApplyDTO, Integer> applyService;

    @Resource(name = "destinationService")
    private IDestinationService destinationService;

    @Resource(name = "teamService")
    private ITeamService teamService;

    @Resource(name = "reportService")
    private IReportService myReportService;

    @Resource(name = "examineService")
    private IExamineService examineService;

    //jsp页面路径定义区
    private final String LIST = "/manager/list";
    private final String SHOW = "/manager/show";
    private final String EDIT = "/manager/edit";

    /**
     * 管理员界面申请列表，除了草稿状态的申请不显示以外，其他的全部显示出来
     *
     * @param request
     * @param user
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model) throws Exception {
        ArrayList<ApplyPreview> applyPreviewList = new ArrayList<>();
        try {
            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyStatus(ApplyStatusEnum.DRAFT.name());
            applyCommand.setDataMark("1");

            List<TApplyEntity> applyEntityList = applyService.query(applyCommand);


            for (int i = 0; i < applyEntityList.size(); i++) {
                TApplyEntity applyEntity = applyEntityList.get(i);
                ApplyPreview applyPreview = new ApplyPreview();

                applyPreview.setId(applyEntity.getId());
                applyPreview.setTotalStatus(applyEntity.getApplyStatus().name());
                applyPreview.setApplyCreateTime(applyEntity.getCreateTime());
                applyPreview.setTeamName(applyEntity.getTeamName());

                // 这里是在生成申请审批进度和总结审批进度
                String applyExamineStatus = ApplyStatusEnum.COMPLETE.getText();
                String reportExamineStatus = ApplyStatusEnum.COMPLETE.getText();
                if (ApplyStatusEnum.UN_CONFIG.name().equals(applyEntity.getApplyStatus().name())) {
                    // 如果这个申请的总的申请状态是un_config，则表明管理员还没有为该申请配置审批流
                    applyExamineStatus = ApplyStatusEnum.UN_CONFIG.getText();
                    reportExamineStatus = ApplyStatusEnum.UN_CONFIG.getText();
                }
                for (TExamineEntity entity : applyEntity.getExamineEntities()) {
                    ApplyStatusEnum result = entity.getResult();
                    if (ExamineTypeEnum.APPLY.name().equals(entity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        // 如果其中一个是待审批，那么整个申请审批进度就是审批中
                        applyExamineStatus = ApplyStatusEnum.PROCESSING.getText();
                        continue;
                    }
                    if (ExamineTypeEnum.REPORT.name().equals(entity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        reportExamineStatus = ApplyStatusEnum.PROCESSING.getText();
                        continue;
                    }
                }

                // 生成报告填写进度
                String isFilledReport = "填写中";
                if (applyEntity.getReportEntities().size() == 0) {
                    isFilledReport = "未填写";
                }
                for (TReportEntity reportEntity : applyEntity.getReportEntities()) {
                    if (ReportEnum.FINAL.equals(reportEntity.getReportType().name())) {
                        isFilledReport = "已填写";
                    }
                }

                applyPreview.setIsFilledReport(isFilledReport);
                applyPreview.setApplyExamineStatus(applyExamineStatus);
                applyPreview.setReportExamineStatus(reportExamineStatus);

                applyPreviewList.add(applyPreview);
            }

        } catch (OtherException e) {
            e.printStackTrace();
            model.addAttribute("applyPreviewListDTO", applyPreviewList);
            return LIST;
        }

        model.addAttribute("applyPreviewListDTO", applyPreviewList);
        return LIST;
    }

    /**
     * 显示某条申请的详细信息
     *
     * @param applyId
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/{applyId}")
    public String show(@PathVariable Integer applyId, HttpServletRequest request, Model model) throws Exception {
        ApplyDetailDTO applyDetailDTO = null;
        try {
            applyDetailDTO = new ApplyDetailDTO();

            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyId(applyId);
            applyCommand.setDataMark("1");
            List<TApplyEntity> applyEntities = applyService.query(applyCommand);

            for (TApplyEntity entity : applyEntities) {
                applyDetailDTO.setId(entity.getId());
                applyDetailDTO.setTeamName(entity.getTeamName());
                applyDetailDTO.setApplyUserName(entity.getApplyUserName());
                applyDetailDTO.setCommissionType(entity.getCommissionType());
                applyDetailDTO.setStartTime(entity.getStartTime());
                applyDetailDTO.setEndTime(entity.getEndTime());
                applyDetailDTO.setReason(entity.getReason());

                // 这里是在生成申请审批进度和总结审批进度
                String applyExamineStatus = ApplyStatusEnum.COMPLETE.name();
                String reportExamineStatus = ApplyStatusEnum.COMPLETE.name();
                if (ApplyStatusEnum.UN_CONFIG.name().equals(entity.getApplyStatus().name())) {
                    // 如果这个申请的总状态是未配置，则这里为"UN_CONFIG"
                    applyExamineStatus = ApplyStatusEnum.UN_CONFIG.name();
                    reportExamineStatus = ApplyStatusEnum.UN_CONFIG.name();
                }
                for (TExamineEntity examineEntity : entity.getExamineEntities()) {
                    ApplyStatusEnum result = examineEntity.getResult();
                    if (ExamineTypeEnum.APPLY.name().equals(examineEntity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        // 如果其中一个是待审批，那么整个申请审批进度就是审批中
                        applyExamineStatus = ApplyStatusEnum.PROCESSING.name();
                        continue;
                    }
                    if (ExamineTypeEnum.REPORT.name().equals(examineEntity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        reportExamineStatus = ApplyStatusEnum.PROCESSING.name();
                        continue;
                    }
                }

                applyDetailDTO.setApplyExamineStatus(applyExamineStatus);
                applyDetailDTO.setReportExamineStatus(reportExamineStatus);

                // 将目的地和队员信息取出
                Destination[] destinations = new Destination[entity.getDestinationEntities().size()];
                for (int i = 0; i < entity.getDestinationEntities().size(); i++) {
                    TDestinationEntity tDestinationEntity = entity.getDestinationEntities().get(i);
                    Destination destination = new Destination();

                    destination.setId(tDestinationEntity.getId());
                    destination.setDestination(tDestinationEntity.getDestination());
                    destination.setNation(tDestinationEntity.getNation());

                    destinations[i] = destination;
                }
                TeamMate[] teamMates = new TeamMate[entity.gettTeamEntities().size()];
                for (int i = 0; i < entity.gettTeamEntities().size(); i++) {
                    TTeamEntity tTeamEntity = entity.gettTeamEntities().get(i);
                    TeamMate teamMate = new TeamMate();
                    teamMate.setId(tTeamEntity.getId());
                    teamMate.setEmployeeId(tTeamEntity.getEmployeeId());
                    teamMate.setEmployeeName(tTeamEntity.getEmployeeName());
                    teamMate.setEmployeeDept(tTeamEntity.getEmployeeDept());
                    teamMate.setEmployeePost(tTeamEntity.getEmployeePost());

                    teamMates[i] = teamMate;
                }

                ArrayList<ExamineProgress> applyExamineProgresses = new ArrayList<>();
                ArrayList<ExamineProgress> reportExamineProgresses = new ArrayList<>();
                for (int i = 0; i < entity.getExamineEntities().size(); i++) {
                    TExamineEntity examineEntity = entity.getExamineEntities().get(i);
                    ExamineProgress progress = new ExamineProgress();
                    progress.setId(examineEntity.getId());
                    progress.setAdvise(examineEntity.getAdvise());
                    progress.setExaminePeopleName(examineEntity.getExaminePeopleName());
                    progress.setPassTime(examineEntity.getPassTime());
                    progress.setRet(examineEntity.getResult().name());
                    progress.setResult(examineEntity.getExamineResult().name());
                    if (examineEntity.getExamineType().name().equals(ExamineTypeEnum.APPLY.name())) {
                        applyExamineProgresses.add(progress);
                    } else if (examineEntity.getExamineType().name().equals(ExamineTypeEnum.REPORT.name())) {
                        reportExamineProgresses.add(progress);
                    }
                }

                Report[] reports = new Report[entity.getReportEntities().size()];
                for (int i = 0; i < entity.getReportEntities().size(); i++) {
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
        } catch (OtherException e) {
            e.printStackTrace();
            model.addAttribute("applyDetailDTO", applyDetailDTO);
            return SHOW;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return SHOW;
    }

    /**
     * 从详情跳转到编辑页面
     *
     * @param applyId
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/edit/{applyId}")
    public String edit(@PathVariable Integer applyId, HttpServletRequest request, Model model) throws Exception {
        ApplyDetailDTO applyDetailDTO = null;
        String message = null;
        try {
            applyDetailDTO = new ApplyDetailDTO();

            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyId(applyId);
            applyCommand.setDataMark("1");
            List<TApplyEntity> applyEntities = applyService.query(applyCommand);

            for (TApplyEntity entity : applyEntities) {
                message = entity.getApplyStatus().name();   // 如果总的状态是草稿，运行用户修改，其他的不允许修改

                applyDetailDTO.setId(entity.getId());
                applyDetailDTO.setTotalStatus(entity.getApplyStatus().name());
                applyDetailDTO.setTeamName(entity.getTeamName());
                applyDetailDTO.setApplyUserName(entity.getApplyUserName());
                applyDetailDTO.setCommissionType(entity.getCommissionType());
                applyDetailDTO.setStartTime(entity.getStartTime());
                applyDetailDTO.setEndTime(entity.getEndTime());
                applyDetailDTO.setReason(entity.getReason());

                // 将目的地和队员信息取出
                Destination[] destinations = new Destination[entity.getDestinationEntities().size()];
                for (int i = 0; i < entity.getDestinationEntities().size(); i++) {
                    TDestinationEntity tDestinationEntity = entity.getDestinationEntities().get(i);
                    Destination destination = new Destination();

                    destination.setId(tDestinationEntity.getId());
                    destination.setDestination(tDestinationEntity.getDestination());
                    destination.setNation(tDestinationEntity.getNation());

                    destinations[i] = destination;
                }
                TeamMate[] teamMates = new TeamMate[entity.gettTeamEntities().size()];
                for (int i = 0; i < entity.gettTeamEntities().size(); i++) {
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
                for (int i = 0; i < entity.getExamineEntities().size(); i++) {
                    TExamineEntity examineEntity = entity.getExamineEntities().get(i);
                    ExamineProgress progress = new ExamineProgress();
                    progress.setId(examineEntity.getId());
                    progress.setAdvise(examineEntity.getAdvise());
                    progress.setExaminePeopleName(examineEntity.getExaminePeopleName());
                    progress.setPassTime(examineEntity.getPassTime());
                    progress.setRet(examineEntity.getResult().name());
                    progress.setResult(examineEntity.getExamineResult().name());
                    if (examineEntity.getExamineType().name().equals(ExamineTypeEnum.APPLY.name())) {
                        applyExamineProgresses.add(progress);
                    } else if (examineEntity.getExamineType().name().equals(ExamineTypeEnum.REPORT.name())) {
                        reportExamineProgresses.add(progress);
                    }
                }

                Report[] reports = new Report[entity.getReportEntities().size()];
                for (int i = 0; i < entity.getReportEntities().size(); i++) {
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
        } catch (OtherException e) {
            e.printStackTrace();
            model.addAttribute("applyDetailDTO", applyDetailDTO);
            model.addAttribute("message", message);
            return EDIT;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        model.addAttribute("message", message);
        return EDIT;
    }

    /**
     * 将管理员配置的审批流存储到数据库里
     * @param applyDetailDTO
     * @param user
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveConfig", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> saveConfig(@RequestBody ApplyDetailDTO applyDetailDTO, CurrentUser user, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
//        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        try {
            // 将新的信息插入
            Timestamp currentTime = DateUtil.getCurrentTime();
            TApplyEntity tApplyEntity = setApplyInfo(applyDetailDTO, user, currentTime);

            for (TDestinationEntity entity : tApplyEntity.getDestinationEntities()) {
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if (entity.getId() != null) {
                    entity.setCreateTime(null);
                }
            }
            for (TTeamEntity entity : tApplyEntity.gettTeamEntities()) {
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if (entity.getId() != null) {
                    entity.setCreateTime(null);
                }
            }
            for (TReportEntity entity : tApplyEntity.getReportEntities()) {
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if (entity.getId() != null) {
                    entity.setCreateTime(null);
                }
            }
            for (TExamineEntity entity : tApplyEntity.getExamineEntities()) {
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if (entity.getId() != null) {
                    entity.setCreateTime(null);
                }
            }
            applyService.createOrUpdate(tApplyEntity);

            // 将要删除的申请的destination和teammates的datamark置为0
            destinationService.delete(applyDetailDTO.getId(), currentTime);
            teamService.delete(applyDetailDTO.getId(), currentTime);
            myReportService.delete(applyDetailDTO.getId(), currentTime);
            examineService.delete(applyDetailDTO.getId(), currentTime);

        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    /**
     * 当用于点击 提交申请 按钮的时候：
     * 1、发出OA
     * 2、将状态改为 PROCESSING
     * @param applyId
     * @param session
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitConfig/{applyId}", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitConfig(@PathVariable Integer applyId, HttpSession session, HttpServletRequest request, Model model){
        Api<Object> api = new Api<>();
        try {
            TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
            tApplyEntity.setApplyStatus(ApplyStatusEnum.PROCESSING);
            tApplyEntity.setId(applyId);
            applyService.update(tApplyEntity);
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }

        return api;
    }

    private TApplyEntity setApplyInfo(@RequestBody ApplyDetailDTO applyDetailDTO, CurrentUser user, Timestamp currentTime) throws Exception {
        TApplyEntity tApplyEntity = new TApplyEntity();

        // 拼接出访的团队名字:首个团员的部门名称+首个团员名字+等x人赴+所有目的地国家(多个国家以、隔开)+任务类型+出访申请 例如:信息服务部冯涛等2人赴美国、加拿大国际会议出访申请
        String teamName = "" + applyDetailDTO.getTeamMates()[0].getEmployeeDept() + applyDetailDTO.getTeamMates()[0].getEmployeeName() + "等" + applyDetailDTO.getTeamMates().length + "人赴";
        if (applyDetailDTO.getDestinations().length == 1) {
            teamName = teamName + applyDetailDTO.getDestinations()[0].getNation();
        } else if (applyDetailDTO.getDestinations().length == 2) {
            teamName = teamName + applyDetailDTO.getDestinations()[0].getNation() + "、" + applyDetailDTO.getDestinations()[0].getNation();
        } else if (applyDetailDTO.getDestinations().length == 2) {
            teamName = teamName + applyDetailDTO.getDestinations()[0].getNation() + "、" + applyDetailDTO.getDestinations()[1].getNation() + "、" + applyDetailDTO.getDestinations()[2].getNation();
        }
        teamName = teamName + applyDetailDTO.getCommissionType() + "出访申请";

        tApplyEntity.setId(applyDetailDTO.getId());
        tApplyEntity.setTeamName(teamName);
        tApplyEntity.setApplyUserId(user.getEmpNo());
        tApplyEntity.setApplyUserName(user.getUserName());
        tApplyEntity.setCommissionType(applyDetailDTO.getCommissionType());
        tApplyEntity.setStartTime(applyDetailDTO.getStartTime());
        tApplyEntity.setEndTime(applyDetailDTO.getEndTime());
        tApplyEntity.setReason(applyDetailDTO.getReason());
        if (applyDetailDTO.getApplyExaminePeoples().length != 0) {
            // 如果申请审批人的人数不是0的话，表示已经配置了申请审批流，则又UN_CONFIG状态变为 已配置但未提交 状态
            tApplyEntity.setApplyStatus(ApplyStatusEnum.CONFIGURED_NOT_SUBMIT);
        }
        tApplyEntity.setCreateTime(currentTime);
        tApplyEntity.setUpdateTime(currentTime);
        tApplyEntity.setDataMark("1");

        ArrayList<TDestinationEntity> tDestinationEntities = new ArrayList<>();
        for (int i = 0; i < applyDetailDTO.getDestinations().length; i++) {
            Destination destination = applyDetailDTO.getDestinations()[i];
            tDestinationEntities.add(new TDestinationEntity(destination.getId(), i, destination.getNation(), destination.getDestination(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
        }
        ArrayList<TTeamEntity> tTeamEntities = new ArrayList<>();
        for (int i = 0; i < applyDetailDTO.getTeamMates().length; i++) {
            TeamMate teamMate = applyDetailDTO.getTeamMates()[i];
            tTeamEntities.add(new TTeamEntity(teamMate.getId(), i, teamMate.getEmployeeId(), teamMate.getEmployeeName(), teamMate.getEmployeeDept(), teamMate.getEmployeePost(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
        }

        // 构造要保存的实体中的report
        ArrayList<TReportEntity> tReportEntities = new ArrayList<>();
        Report[] reports = applyDetailDTO.getReports();
        for (Report report : reports) {
            TReportEntity tReportEntity = new TReportEntity();
            tReportEntity.setId(report.getId());
            tReportEntity.setApplyId(tApplyEntity);
            tReportEntity.setContent(report.getContent());
            tReportEntity.setCreatorId(user.getEmpNo());
            tReportEntity.setDataMark("1");
            tReportEntity.setReportDate(DTFormatUtil.strToDate(DTFormatUtil.getCurrentDate(DTFormatUtil.SDF_YY_MM_DD)));
            tReportEntity.setReportSlot(ReportSlotEnum.EnumFormText(report.getReportSlot()));
            tReportEntity.setReportType(ReportEnum.EnumFormText(report.getReportType()));

            if (tReportEntity.getId() != null || tReportEntity.getId() != 0) {
                // 如果这个report本来就存在的话，就不更新create_time
                tReportEntity.setCreateTime(currentTime);
            }
            tReportEntity.setUpdateTime(currentTime);
            tReportEntities.add(tReportEntity);
        }

        // 构造tApplyEntity需要的TExamineEntity对象
        ArrayList<TExamineEntity> tExamineEntities = new ArrayList<>();
        // 构造申请审批流信息
        ExaminePeople[] applyExaminePeoples = applyDetailDTO.getApplyExaminePeoples();
        for (int i = 0; i < applyExaminePeoples.length; i++) {
            ExaminePeople people = applyExaminePeoples[i];
            TExamineEntity examineEntity = new TExamineEntity();

            // 设置从前端传来的信息到实体中
            examineEntity.setId(people.getId());
            examineEntity.setExaminePeopleId(people.getExaminePeopleId());
            examineEntity.setExaminePeopleName(people.getExaminePeopleName());
            examineEntity.setExaminePeoplePost(people.getExaminePeoplePost());
            examineEntity.setOrders(i);

            // 设置一些通用的信息到实体中
            examineEntity.setDataMark("1");
            examineEntity.setApplyId(tApplyEntity);
            examineEntity.setExamineType(ExamineTypeEnum.APPLY);
            examineEntity.setCreateTime(currentTime);
            examineEntity.setUpdateTime(currentTime);

            tExamineEntities.add(examineEntity);
        }

        // 构造总结审批流信息
        ExaminePeople[] reportExaminePeoples = applyDetailDTO.getReportExaminePeoples();
        for (int i = 0; i < reportExaminePeoples.length; i++) {
            ExaminePeople people = reportExaminePeoples[i];
            TExamineEntity examineEntity = new TExamineEntity();

            // 设置从前端传来的信息到实体中
            examineEntity.setId(people.getId());
            examineEntity.setExaminePeopleId(people.getExaminePeopleId());
            examineEntity.setExaminePeopleName(people.getExaminePeopleName());
            examineEntity.setExaminePeoplePost(people.getExaminePeoplePost());
            examineEntity.setOrders(i);

            // 设置一些通用的信息到实体中
            examineEntity.setDataMark("1");
            examineEntity.setApplyId(tApplyEntity);
            examineEntity.setExamineType(ExamineTypeEnum.REPORT);
            examineEntity.setCreateTime(currentTime);
            examineEntity.setUpdateTime(currentTime);

            tExamineEntities.add(examineEntity);
        }

        tApplyEntity.setDestinationEntities(tDestinationEntities);
        tApplyEntity.settTeamEntities(tTeamEntities);
        tApplyEntity.setReportEntities(tReportEntities);
        tApplyEntity.setExamineEntities(tExamineEntities);
        return tApplyEntity;
    }
}

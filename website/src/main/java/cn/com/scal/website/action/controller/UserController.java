package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ApplyCommand;
import cn.com.scal.components.command.ReportCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.TReportDTO;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.ApplyPreviewListDTO;
import cn.com.scal.components.dto.front.ReportDTO;
import cn.com.scal.components.dto.front.domain.*;
import cn.com.scal.components.enums.ApplyStatusEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;
import cn.com.scal.components.enums.ReportEnum;
import cn.com.scal.components.enums.ReportSlotEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.IDestinationService;
import cn.com.scal.components.service.IReportService;
import cn.com.scal.components.service.ITeamService;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.DTFormatUtil;
import cn.com.scal.components.utils.DateUtil;
import cn.com.scal.components.utils.Pagination;
import cn.com.scal.components.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    private static final int PAGESIZE = 10;  //此处的pagesize应该与pagization里面的pagezi一样，默认为10
    private  int currentPage = 1;

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TApplyEntity, TApplyDTO, Integer> applyService;

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TReportEntity, TReportDTO, Integer> reportService;

    @Resource(name = "destinationService")
    private IDestinationService destinationService;

    @Resource(name = "teamService")
    private ITeamService teamService;

    @Resource(name = "reportService")
    private IReportService myReportService;


    //jsp页面路径定义区
    private final String LIST = "/user/list";
    private final String ADD = "/user/add";
    private final String SHOW = "/user/show";
    private final String EDIT = "/user/edit";

    /**
     * 首页，列表展示
     * @param request
     * @param user
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model) throws Exception {
        String currPage = request.getParameter("currentPage");
        currentPage = StringUtil.isEmpty(currPage) ? 1 : Integer.valueOf(currPage);
        ArrayList<ApplyPreview> applyPreviewList = new ArrayList<>();
        try {
            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyUserId(user.getEmpNo());
            applyCommand.setDataMark("1");

//            List<TApplyEntity> applyEntityList = applyService.query(applyCommand);
            List<TApplyEntity> applyEntityList = applyService.findFlexible(" and dataMark=1 "," id desc ",currentPage,PAGESIZE,TApplyEntity.class);


            for (int i = 0; i < applyEntityList.size(); i++) {
                TApplyEntity applyEntity = applyEntityList.get(i);
                ApplyPreview applyPreview = new ApplyPreview();

                applyPreview.setId(applyEntity.getId());
                applyPreview.setTotalStatus(applyEntity.getApplyStatus().name());
                applyPreview.setApplyCreateTime(applyEntity.getCreateTime());
                applyPreview.setTeamName(applyEntity.getTeamName());

                // 这里是在生成申请审批进度和总结审批进度
                String applyExamineStatus = ApplyStatusEnum.COMPLETE.name();
                String reportExamineStatus = ApplyStatusEnum.COMPLETE.name();
                if(ApplyStatusEnum.DRAFT.name().equals(applyEntity.getApplyStatus().name())){
                    // 如果这个申请的总状态是草稿状态，则这里显示为""
                    applyExamineStatus = "";
                    reportExamineStatus = "";
                }
                for (TExamineEntity entity : applyEntity.getExamineEntities()) {
                    ApplyStatusEnum result = entity.getResult();
                    if (ExamineTypeEnum.APPLY.name().equals(entity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        // 如果其中一个是待审批，那么整个申请审批进度就是审批中
                        applyExamineStatus = ApplyStatusEnum.PROCESSING.name();
                        continue;
                    }
                    if (ExamineTypeEnum.REPORT.name().equals(entity.getExamineType().name()) && ApplyStatusEnum.WAITING.equals(result.name())) {
                        reportExamineStatus = ApplyStatusEnum.PROCESSING.name();
                        continue;
                    }
                }

                // 生成报告填写进度
                String isFilledReport = "填写中";
                if(applyEntity.getReportEntities().size() == 0){
                    isFilledReport = "未填写";
                }
                for(TReportEntity reportEntity: applyEntity.getReportEntities()){
                    if(ReportEnum.FINAL.equals(reportEntity.getReportType().name())){
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

        Pagination pagination = new Pagination();
        model.addAttribute("pageCommand",pagination);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pagesize",PAGESIZE);
        model.addAttribute("count",applyService.findFlexible(" and dataMark=1 "," id desc ",0,0,TApplyEntity.class).size());
        model.addAttribute("applyPreviewListDTO", applyPreviewList);
        return LIST;
    }

    /**
     * 跳转到添加新申请的页面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public String add(Model model) throws Exception {
        return ADD;
    }

    /**
     * 创建新的申请
     * @param applyDTO
     * @param user
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> create(@RequestBody ApplyDTO applyDTO, CurrentUser user, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
//        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        try {
            TApplyEntity tApplyEntity = setApplyInfo(applyDTO, user, DateUtil.getCurrentTime());
            applyService.create(tApplyEntity);
        } catch (OtherException e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    /**
     * 从某条申请的详细信息页面编辑出访申请信息时需要的信息
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


                applyDetailDTO.setDestinations(destinations);
                applyDetailDTO.setTeamMates(teamMates);
            }
        } catch (OtherException e) {
            e.printStackTrace();
            model.addAttribute("message", message);
            return EDIT;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return EDIT;
    }

    /**
     * 显示某一条申请的详细信息
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
                if(ApplyStatusEnum.DRAFT.name().equals(entity.getApplyStatus().name())){
                    // 如果这个申请的总状态是草稿状态，则这里显示为""
                    applyExamineStatus = "未提交";
                    reportExamineStatus = "未提交";
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
     * 提交从详细页面进行编辑后的结果
     * @param applyDTO
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitEdit", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitEdit(@RequestBody ApplyDTO applyDTO, CurrentUser user, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
//        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        try {
            // 将新的信息插入
            Timestamp currentTime = DateUtil.getCurrentTime();
            TApplyEntity tApplyEntity = setApplyInfo(applyDTO, user, currentTime);

            for(TDestinationEntity entity : tApplyEntity.getDestinationEntities()){
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if(entity.getId() != null){
                    entity.setCreateTime(null);
                }
            }
            for(TTeamEntity entity : tApplyEntity.gettTeamEntities()){
                // 如果这条记录本身就存在于数据库中，那么就不要生成create_time
                if(entity.getId() != null){
                    entity.setCreateTime(null);
                }
            }
            applyService.createOrUpdate(tApplyEntity);

            // 将要删除的申请的destination和teammates的datamark置为0
            destinationService.delete(applyDTO.getId(), currentTime);
            teamService.delete(applyDTO.getId(), currentTime);

        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    /**
     * 跳转到新增report页面
     * @param applyId
     * @param session
     * @return
     */
    @RequestMapping(value = "/report/{applyId}")
    @ResponseBody
    public Api<Object> directToReport(@PathVariable Integer applyId, HttpSession session){
        Api<Object> api = new Api<>();
        ReportDTO reportDTO = new ReportDTO();

        try {
            ReportCommand reportCommand = new ReportCommand();
            reportCommand.setApplyId(applyId);
            reportCommand.setDataMark("1");
            List<TReportEntity> reportEntities = reportService.query(reportCommand);


            ArrayList<Report> reportList = new ArrayList<>();
            reportDTO.setApplyId(applyId);
            for(TReportEntity tReportEntity: reportEntities){
                Report report = new Report();
                report.setId(tReportEntity.getId());
                report.setContent(tReportEntity.getContent());
                report.setReportDate(tReportEntity.getReportDate());
                report.setReportSlot(tReportEntity.getReportSlot().name());
                report.setReportType(tReportEntity.getReportType().name());
                reportList.add(report);
            }
            reportDTO.setReports(reportList.toArray(new Report[reportList.size()]));
            api.setData(reportDTO);
        } catch (OtherException e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }

        return api;
    }

    /**
     * 提交新增的report
     * @param reportDTO
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submitReport", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitReport(@RequestBody ReportDTO reportDTO, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        Timestamp currentTime = DateUtil.getCurrentTime();

        try {
            Integer applyId = reportDTO.getApplyId();
            TApplyEntity applyEntity = new TApplyEntity();
            applyEntity.setId(applyId);

            Report[] reports = reportDTO.getReports();
            for (Report report : reports) {
                TReportEntity tReportEntity = new TReportEntity();
                tReportEntity.setId(report.getId());
                tReportEntity.setApplyId(applyEntity);
                tReportEntity.setContent(report.getContent());
//                tReportEntity.setCreatorId(user.getEmpNo());
                tReportEntity.setCreatorId("007955");
                tReportEntity.setDataMark("1");
                tReportEntity.setReportDate(new Date());
                tReportEntity.setReportType(ReportEnum.EnumFormName(report.getReportType()));
                if(StringUtil.isEmpty(report.getReportSlot()) && ReportEnum.TRIP.name().equals(report.getReportType())){
                    throw new Exception("行程时段不能为空！");
                }else if(!StringUtil.isEmpty(report.getReportSlot()) && ReportEnum.TRIP.name().equals(report.getReportType())){
                    tReportEntity.setReportSlot(ReportSlotEnum.EnumFormText(report.getReportSlot()));
                }


                if(tReportEntity.getId() != null){
                    // 如果这个report本来就存在的话，就不更新create_time
                    tReportEntity.setCreateTime(currentTime);
                }
                tReportEntity.setUpdateTime(currentTime);

                reportService.createOrUpdate(tReportEntity);
            }

            myReportService.delete(applyId, currentTime);
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }

        return api;
    }

    /**
     * 提交申请，就是将此条申请又草稿状态变成un_config状态
     * @param applyId
     * @param session
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitApply/{applyId}", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitApply(@PathVariable Integer applyId, HttpSession session, HttpServletRequest request, Model model){
        Api<Object> api = new Api<>();
        try {
            TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
            tApplyEntity.setApplyStatus(ApplyStatusEnum.UN_CONFIG);
            applyService.update(tApplyEntity);
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }

        return api;
    }


    private TApplyEntity setApplyInfo(@RequestBody ApplyDTO applyDTO, CurrentUser user, Timestamp currentTime) {
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

        tApplyEntity.setId(applyDTO.getId());
        tApplyEntity.setTeamName(teamName);
//        tApplyEntity.setApplyUserId(user.getEmpNo());
        tApplyEntity.setApplyUserId("015074");
//        tApplyEntity.setApplyUserName(user.getUserName());
        tApplyEntity.setApplyUserName("邹江华");
        tApplyEntity.setCommissionType(applyDTO.getCommissionType());
        tApplyEntity.setStartTime(applyDTO.getStartTime());
        tApplyEntity.setEndTime(applyDTO.getEndTime());
        tApplyEntity.setReason(applyDTO.getReason());
        tApplyEntity.setApplyStatus(ApplyStatusEnum.DRAFT);
        tApplyEntity.setCreateTime(currentTime);
        tApplyEntity.setUpdateTime(currentTime);
        tApplyEntity.setDataMark("1");

        ArrayList<TDestinationEntity> tDestinationEntities = new ArrayList<>();
        for (int i = 0; i < applyDTO.getDestinations().length; i++) {
            Destination destination = applyDTO.getDestinations()[i];
            tDestinationEntities.add(new TDestinationEntity(destination.getId(), i, destination.getNation(), destination.getDestination(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
        }
        ArrayList<TTeamEntity> tTeamEntities = new ArrayList<>();
        for (int i = 0; i < applyDTO.getTeamMates().length; i++) {
            TeamMate teamMate = applyDTO.getTeamMates()[i];
            tTeamEntities.add(new TTeamEntity(teamMate.getId(), i, teamMate.getEmployeeId(), teamMate.getEmployeeName(), teamMate.getEmployeeDept(), teamMate.getEmployeePost(), DateUtil.getCurrentTime(), DateUtil.getCurrentTime(), "1", tApplyEntity));
        }


        tApplyEntity.setDestinationEntities(tDestinationEntities);
        tApplyEntity.settTeamEntities(tTeamEntities);
        return tApplyEntity;
    }
}

package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ApplyCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.domain.*;
import cn.com.scal.components.enums.*;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.*;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.AESUtils;
import cn.com.scal.components.utils.DTFormatUtil;
import cn.com.scal.components.utils.DateUtil;
import cn.com.scal.components.webservice.WFInterfaceEntity;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("{AES_KEY}")
    private String aesKey;

    @Value("{URL}")
    private String url;

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

    @Resource(name = "OAService")
    private IOAService oaService;

    //jsp页面路径定义区
    private final String LIST = "/manager/list";
    private final String SHOW = "/manager/show";
    private final String EDIT = "/manager/edit";

    /**
     * 管理员界面申请列表，除了草稿状态的申请不显示以外，其他的全部显示出来
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String list(Model model) throws Exception {
        ArrayList<ApplyPreview> applyPreviewList = new ArrayList<>();
        String message;
        try {
            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setStage(StageEnum.DRAFT.name());
            applyCommand.setDataMark("1");

            List<TApplyEntity> applyEntityList = applyService.query(applyCommand);


            for (int i = 0; i < applyEntityList.size(); i++) {
                TApplyEntity applyEntity = applyEntityList.get(i);
                ApplyPreview applyPreview = new ApplyPreview();

                applyPreview.setId(applyEntity.getId());
                applyPreview.setTotalStatus(applyEntity.getStage().name());
                applyPreview.setApplyCreateTime(applyEntity.getCreateTime());
                applyPreview.setTeamName(applyEntity.getTeamName());
                applyPreview.setIsFilledReport(applyEntity.getReportFillStatus().getText());
                applyPreview.setApplyExamineStatus(applyEntity.getApplyStatus().getText());
                applyPreview.setReportExamineStatus(applyEntity.getReportStatus().getText());

                applyPreviewList.add(applyPreview);
            }

        } catch (OtherException e) {
            message = e.getMessage();
            model.addAttribute("message", message);
            return LIST;
        }

        model.addAttribute("applyPreviewListDTO", applyPreviewList);
        return LIST;
    }

    /**
     * 显示某条申请的详细信息
     *
     * @param applyId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/{applyId}")
    public String show(@PathVariable Integer applyId, Model model) throws Exception {
        ApplyDetailDTO applyDetailDTO;
        String message;
        try {
            applyDetailDTO = new ApplyDetailDTO();
            TApplyEntity entity = applyService.load(TApplyEntity.class, applyId);


            applyDetailDTO.setId(entity.getId());
            applyDetailDTO.setTeamName(entity.getTeamName());
            applyDetailDTO.setApplyUserName(entity.getApplyUserName());
            applyDetailDTO.setCommissionType(entity.getCommissionType());
            applyDetailDTO.setStartTime(entity.getStartTime());
            applyDetailDTO.setEndTime(entity.getEndTime());
            applyDetailDTO.setReason(entity.getReason());
            applyDetailDTO.setApplyExamineStatus(entity.getApplyStatus().getText());
            applyDetailDTO.setReportExamineStatus(entity.getReportStatus().getText());

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
                progress.setRet(examineEntity.getStatus().getText());
                progress.setResult(examineEntity.getExamineResult().getText());
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

        } catch (OtherException e) {
            message = e.getMessage();
            model.addAttribute("message", message);
            return SHOW;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return SHOW;
    }

    /**
     * 从详情跳转到编辑页面
     *
     * @param applyId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/edit/{applyId}")
    public String edit(@PathVariable Integer applyId, Model model) throws Exception {
        ApplyDetailDTO applyDetailDTO;
        String message;
        try {
            applyDetailDTO = new ApplyDetailDTO();
            TApplyEntity entity = applyService.load(TApplyEntity.class, applyId);


            applyDetailDTO.setId(entity.getId());
            applyDetailDTO.setTeamName(entity.getTeamName());
            applyDetailDTO.setApplyUserName(entity.getApplyUserName());
            applyDetailDTO.setCommissionType(entity.getCommissionType());
            applyDetailDTO.setStartTime(entity.getStartTime());
            applyDetailDTO.setEndTime(entity.getEndTime());
            applyDetailDTO.setReason(entity.getReason());
            applyDetailDTO.setApplyExamineStatus(entity.getApplyStatus().getText());
            applyDetailDTO.setReportExamineStatus(entity.getReportStatus().getText());

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
                progress.setRet(examineEntity.getStatus().getText());
                progress.setResult(examineEntity.getExamineResult().getText());
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

        } catch (OtherException e) {
            message = e.getMessage();
            model.addAttribute("message", message);
            return EDIT;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return EDIT;
    }

    /**
     * 将管理员配置的申请审批流存储到数据库里
     *
     * @param applyDetailDTO
     * @param user
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveApplyConfig", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> saveApplyConfig(@RequestBody ApplyDetailDTO applyDetailDTO, CurrentUser user, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
//        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        try {
            // 将新的信息插入
            Timestamp currentTime = DateUtil.getCurrentTime();
            TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyDetailDTO.getId());
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
            tApplyEntity.setExamineEntities(tExamineEntities);
            tApplyEntity.setApplyStatus(ApplyAndReportTotalExamineStatusEnum.CONFIGURED_NOT_SUBMIT);
            applyService.createOrUpdate(tApplyEntity);

            // 将要删除的申请的申请审批人的datamark置为0
            examineService.delete(applyDetailDTO.getId(), currentTime, ExamineTypeEnum.APPLY.name());

        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    /**
     * 将管理员配置的总结审批流存储到数据库里
     *
     * @param applyDetailDTO
     * @param user
     * @param session
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveReportConfig", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> saveReportConfig(@RequestBody ApplyDetailDTO applyDetailDTO, CurrentUser user, HttpSession session, HttpServletRequest request, Model model) throws Exception {
        Api<Object> api = new Api<>();
//        CurrentUser user = (CurrentUser) session.getAttribute("currentUser");
        try {
            // 将新的信息插入
            Timestamp currentTime = DateUtil.getCurrentTime();
            TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyDetailDTO.getId());
            // 构造tApplyEntity需要的TExamineEntity对象
            ArrayList<TExamineEntity> tExamineEntities = new ArrayList<>();
            // 构造申请审批流信息
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
            tApplyEntity.setExamineEntities(tExamineEntities);
            tApplyEntity.setReportStatus(ApplyAndReportTotalExamineStatusEnum.CONFIGURED_NOT_SUBMIT);
            applyService.createOrUpdate(tApplyEntity);

            // 将要删除的总结的申请审批人的datamark置为0
            examineService.delete(applyDetailDTO.getId(), currentTime, ExamineTypeEnum.REPORT.name());

        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }
        return api;
    }

    /**
     * 当用户点击 提交申请审批 按钮的时候：
     * 0、检查是否配置了审批人
     * 1、发出OA
     * 2、将状态改为 PROCESSING
     *
     * @param applyId
     * @param session
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitApplyConfig/{applyId}", method = RequestMethod.POST)
    @ResponseBody
    public Api<Object> submitConfig(@PathVariable Integer applyId, HttpSession session, HttpServletRequest request, Model model) {
        Api<Object> api = new Api<>();
        try {
            TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
            if (StageEnum.APPLY_EXAMINE.equals(tApplyEntity.getStage().name())) {
                // 如果当前这条申请处于配置了审批流但未提交状态，则可以改变发送oa待办事项，并修改状态

                // 向oa发送代办事项
                TExamineEntity examinePeople = tApplyEntity.getExamineEntities().get(0);

                String examinePeopleId = examinePeople.getExaminePeopleId();// 获取要发送oa的员工的oa号
                String examinePeopleIdEncrypted = AESUtils.AESEncode(aesKey, examinePeopleId);
                String examinePeopleName = examinePeople.getExaminePeopleName();
                String fromUrl = String.format(this.url, examinePeopleIdEncrypted, examinePeopleName, applyId);

                // 这个entity中需要配置的属性有：InstanceTitle、FormURL、UserID、UserName、UserDeptName
                // CreatID、CreatName、CreatDeptName、CreatPhone、CreatTime
                WFInterfaceEntity entity = new WFInterfaceEntity();


                oaService.createOAItem(entity);

                // 修改这条申请的状态
                tApplyEntity.setApplyStatus(ApplyAndReportTotalExamineStatusEnum.PROCESSING);
                tApplyEntity.setId(applyId);
                applyService.update(tApplyEntity);
            }
            throw new Exception("还未配置申请审批流！");
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
        }

        return api;
    }

}

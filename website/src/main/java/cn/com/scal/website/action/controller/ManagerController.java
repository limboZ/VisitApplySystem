package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ApplyCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.domain.*;
import cn.com.scal.components.enums.ApplyStatusEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;
import cn.com.scal.components.enums.ReportEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    //jsp页面路径定义区
    private final String LIST = "/manager/list";
    private final String SHOW = "/manager/show";
    private final String EDIT = "/manager/edit";

    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model)throws Exception{
        ArrayList<ApplyPreview> applyPreviewList = new ArrayList<>();
        try {
            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyStatus(ApplyStatusEnum.DRAT.name());
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
                String applyExamineStatus = ApplyStatusEnum.COMPLETE.name();
                String reportExamineStatus = ApplyStatusEnum.COMPLETE.name();
                if(ApplyStatusEnum.UN_CONFIG.name().equals(applyEntity.getApplyStatus().name())){
                    // 如果这个申请的总的申请状态是un_config，则表明管理员还没有为该申请配置审批流
                    applyExamineStatus = ApplyStatusEnum.UN_CONFIG.name();
                    reportExamineStatus = ApplyStatusEnum.UN_CONFIG.name();
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

        model.addAttribute("applyPreviewListDTO", applyPreviewList);
        return LIST;
    }

    @RequestMapping("/show/{applyId}")
    public String show(@PathVariable Integer applyId, HttpServletRequest request, Model model)throws Exception{
        ApplyDetailDTO applyDetailDTO = null;
        try {
            applyDetailDTO = new ApplyDetailDTO();

            ApplyCommand applyCommand = new ApplyCommand();
            applyCommand.setApplyId(applyId);
            applyCommand.setDataMark("1");
            List<TApplyEntity> applyEntities = applyService.query(applyCommand);

            for (TApplyEntity entity : applyEntities) {
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
            return SHOW;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return SHOW;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,Model model)throws Exception{
        return EDIT;
    }
}

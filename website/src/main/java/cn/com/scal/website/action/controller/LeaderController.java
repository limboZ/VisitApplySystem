package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.ExamineCommand;
import cn.com.scal.components.domain.*;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.TExamineDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.domain.Destination;
import cn.com.scal.components.dto.front.domain.ExamineProgress;
import cn.com.scal.components.dto.front.domain.Report;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.enums.ApplyAndReportTotalExamineStatusEnum;
import cn.com.scal.components.enums.ExamineTypeEnum;
import cn.com.scal.components.enums.StageEnum;
import cn.com.scal.components.exception.OtherException;
import cn.com.scal.components.service.IOAService;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.AESUtils;
import cn.com.scal.components.utils.DTFormatUtil;
import cn.com.scal.components.webservice.WFInterfaceEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by limboZ on 2017/10/11.
 */
@Controller
@RequestMapping("/leader")
public class LeaderController {

    @Value("{URL}")
    private String url;

    @Value("{AES_KEY}")
    private String aes_key;

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TExamineEntity, TExamineDTO, Integer> examineService;

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TApplyEntity, TApplyDTO, Integer> applyService;

    @Resource(name = "OAService")
    private IOAService oaService;

    //jsp页面路径定义区
    private final String CHECK = "/leader/check";

    /**
     * 领导查看审批页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/show/{totalExamineNum}/{currentOrder}/{applyId}/{examineType}/{examineId}")
    public String show(@PathVariable Integer totalExamineNum,
                       @PathVariable Integer currentOrder,
                       @PathVariable Integer applyId,
                       @PathVariable String examineType,
                       @PathVariable Integer examineId,
                       @PathVariable String creatorNum,
                       @PathVariable String creatorName,
                       @PathVariable String creatorDept,
                       Model model) throws Exception {
        model.addAttribute("totalExamineNum", totalExamineNum);
        model.addAttribute("currentOrder", currentOrder);
        model.addAttribute("examineType", examineType);
        model.addAttribute("examineId", examineId);
        model.addAttribute("examineId", creatorNum);
        model.addAttribute("examineId", creatorName);
        model.addAttribute("examineId", creatorDept);
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
            return CHECK;
        }

        model.addAttribute("applyDetailDTO", applyDetailDTO);
        return CHECK;
    }


    /**
     * 领导同意审批页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaderPass/{totalExamineNum}/{currentOrder}/{applyId}/{examineType}/{examineId}")
    public void leaderPass(@PathVariable Integer totalExamineNum,
                           @PathVariable Integer currentOrder,
                           @PathVariable Integer applyId,
                           @PathVariable String examineType,
                           @PathVariable String examineId,
                           @PathVariable String creatorNumEntrypted,
                           @PathVariable String creatorName,
                           @PathVariable String creatorDept) throws Exception {
        if (ExamineTypeEnum.APPLY.name().equals(examineType)) {
            // 关闭OA待办
            oaService.completeOAItem(examineId);

            // 如果是申请审批
            if (currentOrder == totalExamineNum - 1) {
                // 如果是最后一个审批人，则并将出访阶段设置为 填写总结，和申请状态设置为 审批完成，和总结状态设置为 待配置
                TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
                tApplyEntity.setStage(StageEnum.REPORT);
                tApplyEntity.setApplyStatus(ApplyAndReportTotalExamineStatusEnum.COMPLETE);
                tApplyEntity.setReportStatus(ApplyAndReportTotalExamineStatusEnum.WAITING_CONFIG);
                applyService.update(tApplyEntity);

            } else {
                // 如果不是最后一个审批人，查出下一个审批人，发送OA待办
                TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
                ExamineCommand examineCommand = new ExamineCommand();
                examineCommand.setDataMark("1");
                examineCommand.setApplyId(applyId);
                examineCommand.setOrders(currentOrder + 1);
                examineCommand.setExamineType(ExamineTypeEnum.APPLY.name());
                List<TExamineEntity> tExamineEntities = examineService.query(examineCommand);
                TExamineEntity examineEntity = tExamineEntities.get(0);

                // 这个entity中需要配置的属性有：InstanceID、StepID、InstanceTitle、FormURL、UserID、UserName、UserDeptName
                // CreatID、CreatName、CreatDeptName、CreatTime
                WFInterfaceEntity entity = new WFInterfaceEntity();

                entity.setInstanceID(String.valueOf(examineEntity.getId()));
                entity.setStepID(String.valueOf(examineEntity.getId()));
                entity.setInstanceTitle(tApplyEntity.getTeamName());
                // http://loocalhost:8080/show/%d/%d/%d/%s/%d    总的审批人数、当前审批人顺序、本条的出访的id、审批类型、审批item的id、管理员工号、管理员名字、管理员部门
                String fromUrl = String.format(this.url,
                        totalExamineNum,
                        examineEntity.getOrders(),
                        applyId,
                        ExamineTypeEnum.APPLY.name(),
                        examineEntity.getId(),
                        creatorNumEntrypted,
                        creatorName,
                        creatorDept);
                entity.setFormURL(fromUrl);
                entity.setUserID(examineEntity.getExaminePeopleId());
                entity.setUserName(examineEntity.getExaminePeopleName());
                entity.setUserDeptName(examineEntity.getExamineDept());
                entity.setCreatID(AESUtils.AESDncode(this.aes_key, creatorNumEntrypted));
                entity.setCreatName(creatorName);
                entity.setCreatDeptName(creatorDept);
                entity.setCreatTime(DTFormatUtil.convertStrBySdf(new Date(), DTFormatUtil.SDF_ALL));

                entity.setInstanceID("vas20171018abcd");
                entity.setStepID("vas20171018abcd");
                entity.setCreatID("015073");
                entity.setCreatName("李程鹏");
                entity.setCreatDeptName("信息服务部");
                entity.setCreatTime("2017-10-20 15:12:20");

                oaService.createOAItem(entity);

            }
        } else {
            // 如果是总结审批
            if (currentOrder == totalExamineNum - 1) {
                // 如果是最后一个审批人，则关闭OA待办，并将出访总状态设置为 完成，和总结状态设置为 审批完成
                oaService.completeOAItem(examineId);
                TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
                tApplyEntity.setStage(StageEnum.COMPLETE);
                tApplyEntity.setReportStatus(ApplyAndReportTotalExamineStatusEnum.COMPLETE);
                applyService.update(tApplyEntity);
            } else {
                // 如果不是最后一个审批人，则关闭OA待办，并查出下一个审批人，发送OA待办
                TApplyEntity tApplyEntity = applyService.load(TApplyEntity.class, applyId);
                ExamineCommand examineCommand = new ExamineCommand();
                examineCommand.setDataMark("1");
                examineCommand.setApplyId(applyId);
                examineCommand.setOrders(currentOrder + 1);
                examineCommand.setExamineType(ExamineTypeEnum.REPORT.name());
                List<TExamineEntity> tExamineEntities = examineService.query(examineCommand);
                TExamineEntity examineEntity = tExamineEntities.get(0);

                // 这个entity中需要配置的属性有：InstanceID、StepID、InstanceTitle、FormURL、UserID、UserName、UserDeptName
                // CreatID、CreatName、CreatDeptName、CreatTime
                WFInterfaceEntity entity = new WFInterfaceEntity();

                entity.setInstanceID(String.valueOf(examineEntity.getId()));
                entity.setStepID(String.valueOf(examineEntity.getId()));
                entity.setInstanceTitle(tApplyEntity.getTeamName());
                // http://loocalhost:8080/show/%d/%d/%d/%s/%d    总的审批人数、当前审批人顺序、本条的出访的id、审批类型、审批item的id、管理员工号、管理员名字、管理员部门
                String fromUrl = String.format(this.url,
                        totalExamineNum,
                        examineEntity.getOrders(),
                        applyId,
                        ExamineTypeEnum.APPLY.name(),
                        examineEntity.getId(),
                        creatorNumEntrypted,
                        creatorName,
                        creatorDept);
                entity.setFormURL(fromUrl);
                entity.setUserID(examineEntity.getExaminePeopleId());
                entity.setUserName(examineEntity.getExaminePeopleName());
                entity.setUserDeptName(examineEntity.getExamineDept());
                entity.setCreatID(AESUtils.AESDncode(this.aes_key, creatorNumEntrypted));
                entity.setCreatName(creatorName);
                entity.setCreatDeptName(creatorDept);
                entity.setCreatTime(DTFormatUtil.convertStrBySdf(new Date(), DTFormatUtil.SDF_ALL));

//                entity.setInstanceID("vas20171018abcd");
//                entity.setStepID("vas20171018abcd");
//                entity.setCreatID("015073");
//                entity.setCreatName("李程鹏");
//                entity.setCreatDeptName("信息服务部");
//                entity.setCreatTime("2017-10-20 15:12:20");

                oaService.createOAItem(entity);
            }
        }

    }
}

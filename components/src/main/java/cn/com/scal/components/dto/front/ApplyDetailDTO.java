package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.*;

/**
 * 1、用于返回给前端出访申请详情，和前端修改后接收前端发送过来的被修改后的出访详情内容
 * 2、用户界面和管理员界面的都统一在了这个类里面
 */
public class ApplyDetailDTO {
    private String teamName;
    private String applyUserName;
    private String commissionType;
    private String predict_visit_time;
    private String reason;

    private Destination[] destinations;
    private TeamMate[] teamMates;

    private ExaminePeople[] applyExaminePeoples;

    private ExamineProgress[] applyExamineProgresses;

    private Report[] reports;

    private ExaminePeople[] reportExaminePeoples;

    private ExamineProgress[] reportExamineProgresses;
}

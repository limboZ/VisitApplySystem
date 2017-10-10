package cn.com.scal.components.service;

import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.ApplyPreviewListDTO;

public interface IApplyService extends ICommonService<TApplyEntity, TApplyDTO, Integer> {
    /**
     * 从ApplyEntity中取出某一个人的所有申请信息
     * @param applyUserId   登陆者的工号
     * @return  要在前端展示的信息的实体
     */
    ApplyPreviewListDTO getApplyPreviewList(Integer applyUserId);

    /**
     * 获取某条申请的详细信息，包括申请的基本信息、目的地、成员、申请审批流信息、总结审批流信息
     * @param applyId   某条申请的主键
     * @return  要在前端展示的信息的实体
     */
    ApplyDetailDTO getApplyDetail(Integer applyId);

    /**
     * 将某条新建的申请存入到数据库中，存入的信息包括申请的基本信息，目的地、成员、申请审批流信息、总结审批流信息
     * @param applyDTO  新建的申请的前端实体
     */
    void setNewApply(ApplyDTO applyDTO);

    /**
     * 更新某条申请，并存入到数据库中，存入的信息包括申请的基本信息，目的地、成员、申请审批流信息、总结审批流信息
     * @param applyDetailDTO  修改的申请的前端实体
     */
    void updateApply(ApplyDetailDTO applyDetailDTO);
}

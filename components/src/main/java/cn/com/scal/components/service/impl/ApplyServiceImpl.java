package cn.com.scal.components.service.impl;

import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.ApplyDetailDTO;
import cn.com.scal.components.dto.front.ApplyPreviewListDTO;
import cn.com.scal.components.service.IApplyService;
import org.springframework.stereotype.Service;

@Service("applyService")
public class ApplyServiceImpl implements IApplyService {
    @Override
    public ApplyPreviewListDTO getApplyPreviewList(String applyUserId) {
        return null;
    }

    @Override
    public ApplyDetailDTO getApplyDetail(Integer applyId) {
        return null;
    }

    @Override
    public void setNewApply(ApplyDTO applyDTO) {

    }

    @Override
    public void updateApply(ApplyDetailDTO applyDetailDTO) {

    }
}

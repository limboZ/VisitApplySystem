package cn.com.scal.components.dto.front;

import cn.com.scal.components.dto.front.domain.ApplyPreview;

/**
 * 用于向前端放置出访申请列表的内容
 */
public class ApplyPreviewListDTO {
    private ApplyPreview[] applyPreviews;

    public ApplyPreviewListDTO() {
    }

    public ApplyPreview[] getApplyPreviews() {
        return applyPreviews;
    }

    public void setApplyPreviews(ApplyPreview[] applyPreviews) {
        this.applyPreviews = applyPreviews;
    }
}

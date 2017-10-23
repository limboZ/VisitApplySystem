package cn.com.scal.website.action.controller.Api;

import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.domain.TCommissionTypeEnumEntity;
import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.TCommissionTypeEnumDTO;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import cn.com.scal.components.utils.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CommissionTypeController {
    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TCommissionTypeEnumEntity, TCommissionTypeEnumDTO, Integer> commissionTypesService;

    /**
     * 从数据库获取出访的类型
     * @return
     */
    @RequestMapping(value = "/commissionTypes", method = RequestMethod.GET)
    @ResponseBody
    public Api<Object> commissionTypes(){
        Api<Object> api = new Api<>();
        Object[] objects = new Object[0];

        try {
            List<TCommissionTypeEnumEntity> all = commissionTypesService.all(TCommissionTypeEnumEntity.class);
            objects = all.toArray();
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(e.getMessage());
            api.setData(objects);
        }

        if(0 == objects.length){
            api.setCode(Api.ERROR_CODE);
            api.setTip(ErrorMessage.ERROR_NO_DATA);
            api.setData(objects);
        }else {
            api.setData(objects);
        }


        return api;
    }
}

package cn.com.scal.website.action.controller.Api;

import cn.com.scal.components.dto.Api;
import cn.com.scal.components.dto.front.domain.TeamMate;
import cn.com.scal.components.service.IOAService;
import cn.com.scal.components.utils.ErrorMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class OAController {
    @Resource(name = "OAService")
    IOAService oaService;

    @RequestMapping(value = "/oaPeopleInfo/{OANum}", method = RequestMethod.GET)
    @ResponseBody
    public Api<Object> oaPeopleInfo(@PathVariable String OANum){
        Api<Object> api = new Api<>();
        TeamMate teamMate = null;

        try {
            teamMate = oaService.getInfoFromOA(OANum);
            api.setData(teamMate);
        } catch (Exception e) {
            api.setCode(Api.ERROR_CODE);
            api.setTip(ErrorMessage.ERROR);
            api.setData(e.getMessage());
        }
        if(teamMate == null){
            api.setCode(Api.ERROR_CODE);
            api.setTip(ErrorMessage.ERROR_NO_DATA);
            api.setData(teamMate);
        }
        return api;
    }
}

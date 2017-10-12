package cn.com.scal.website.action.controller;

import cn.com.scal.components.domain.CurrentUser;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.dto.front.ApplyPreviewListDTO;
import cn.com.scal.components.service.IApplyService;
import cn.com.scal.components.service.IReportService;
import org.hibernate.metamodel.source.annotations.xml.mocker.MockHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Resource(name = "applyService")
    private IApplyService applyService;

    @Resource(name = "reportService")
    private IReportService reportService;

    //jsp页面路径定义区
    private final String LIST = "/user/list";
    private final String ADD = "/user/add";
    private final String SHOW = "/user/show";
    private final String EDIT = "/user/edit";

    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model)throws Exception{
        ApplyPreviewListDTO applyPreviewList = applyService.getApplyPreviewList(user.getEmpNo());
        model.addAttribute("applyPreviewList", applyPreviewList);
        return LIST;
    }

    @RequestMapping("/add")
    public String add(Model model)throws Exception{
        return ADD;
    }

    @RequestMapping("/creat")
    public String ceate(@RequestBody ApplyDTO applyDTO, HttpServletRequest request, Model model)throws Exception{
        applyService.setNewApply(applyDTO);
        return LIST;
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request,Model model)throws Exception{
        return SHOW;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,Model model)throws Exception{
        return EDIT;
    }
}

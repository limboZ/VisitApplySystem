package cn.com.scal.website.action.controller;

import cn.com.scal.components.domain.CurrentUser;
import cn.com.scal.components.domain.TApplyEntity;
import cn.com.scal.components.dto.TApplyDTO;
import cn.com.scal.components.dto.front.ApplyDTO;
import cn.com.scal.components.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Resource(name = "commonServiceImpl")
    private CommonServiceImpl<TApplyEntity, TApplyDTO, Integer> applyService;

    //jsp页面路径定义区
    private final String LIST = "/user/list";
    private final String ADD = "/user/add";
    private final String SHOW = "/user/show";
    private final String EDIT = "/user/edit";

    @RequestMapping("/list")
    public String list(HttpServletRequest request, CurrentUser user, Model model) throws Exception {
        return LIST;
    }

    @RequestMapping("/add")
    public String add(Model model) throws Exception {
        return ADD;
    }

    @RequestMapping("/creat")
    public String ceate(@RequestBody ApplyDTO applyDTO, HttpServletRequest request, Model model) throws Exception {
        return LIST;
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request, Model model) throws Exception {
        return SHOW;
    }

    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, Model model) throws Exception {
        return EDIT;
    }
}

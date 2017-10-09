package cn.com.scal.website.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

    //jsp页面路径定义区
    private final String LIST = "/manager/list";
    private final String SHOW = "/manager/show";

    @RequestMapping("/list")
    public String list(HttpServletRequest request,Model model)throws Exception{
        return LIST;
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request,Model model)throws Exception{
        return SHOW;
    }
}

package cn.com.scal.website.action.controller;

import org.hibernate.metamodel.source.annotations.xml.mocker.MockHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by limboZ on 2017/9/28.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    //jsp页面路径定义区
    private final String LIST = "/user/list";
    private final String ADD = "/user/add";
    private final String SHOW = "/user/show";

    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model)throws Exception{
        return LIST;
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request,Model model)throws Exception{
        return ADD;
    }

    @RequestMapping("/show")
    public String show(HttpServletRequest request,Model model)throws Exception{
        return SHOW;
    }
}

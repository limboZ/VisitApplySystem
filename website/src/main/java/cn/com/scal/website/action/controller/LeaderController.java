package cn.com.scal.website.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by limboZ on 2017/10/11.
 */
@Controller
@RequestMapping("/leader")
public class LeaderController {

    //jsp页面路径定义区
    private final String CHECK = "/leader/check";

    /**
     * 领导查看审批页面
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/show")
    public String show(HttpServletRequest request)throws Exception{
        return CHECK;
    }
}

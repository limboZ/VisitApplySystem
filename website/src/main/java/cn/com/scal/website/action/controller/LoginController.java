package cn.com.scal.website.action.controller;

import cn.com.scal.components.domain.CurrentUser;
import cn.com.scal.components.utils.Consts;
import cn.com.scal.components.utils.ErrorMessage;
import cn.com.scal.components.utils.StringUtil;
import cn.com.scal.components.utils.webservice.PortalLoginUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vslimit on 15/9/18.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController{

    private static final String LOGIN = "login";
    @RequestMapping(method = RequestMethod.GET)
    public String login(CurrentUser currentUser,Model model) {
        model.addAttribute(Consts.CURRENT_USER,currentUser);
        return LOGIN;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String signIn(CurrentUser user,Model model,HttpSession session) throws IOException, DocumentException {
        if (null == user || StringUtil.isEmpty(user.getEmpNo()) || StringUtil.isEmpty(user.getPassword())) {
            model.addAttribute(Consts.MESSAGE, ErrorMessage.EMPTY_LOGIN);
            return LOGIN;
        }else {
            PortalLoginUtil util = new PortalLoginUtil();
            boolean re = util.validLogin(user.getEmpNo(), user.getPassword());
            if (re) {
                session.setAttribute(Consts.CURRENT_USER, user);
            }else {
                model.addAttribute(Consts.MESSAGE, ErrorMessage.ERROR_LOGIN);
                return LOGIN;
            }
        }
        return REDIRECT + HomeController.HOME;
    }
}

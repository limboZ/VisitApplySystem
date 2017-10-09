package cn.com.scal.website.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by vslimit on 15/9/23.
 */

@Controller
@RequestMapping("/home")
public class HomeController extends AbstractController {
    public static final String HOME = "home";

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        System.out.println(111);
        return HOME;
    }
}

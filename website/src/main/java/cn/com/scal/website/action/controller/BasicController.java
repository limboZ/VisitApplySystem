package cn.com.scal.website.action.controller;

import cn.com.scal.components.command.BaseCommand;
import cn.com.scal.components.domain.Base;
import cn.com.scal.components.dto.BaseDTO;
import cn.com.scal.components.service.ICommonService;
import cn.com.scal.components.utils.Consts;
import cn.com.scal.components.utils.Message;
import cn.com.scal.components.utils.Pagination;
import cn.com.scal.components.utils.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by vslimit on 15/4/23.
 */

public abstract class BasicController<T extends Base<T>, K extends BaseDTO<K, T>, P extends BaseCommand<T, K>> extends AbstractController{

    protected Integer currentPage = 1;

    @Resource(name = "commonServiceImpl")
    protected ICommonService<T, K, Integer> commonService;

    protected static final String INDEX = "/index";
    protected static final String SHOW = "/show";
    protected static final String FORM = "/form";

    protected String templates(String name) {
        return getClazz().getSimpleName().toLowerCase() + name;
    }
    protected String baseUrl() {
        return getClazz().getSimpleName().toLowerCase() + "s";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model, P p) throws Exception {
        String queryString = request.getQueryString();
        if (!StringUtil.isEmpty(queryString)) {
            BeanUtils.copyProperties(p, StringUtil.queryStringToMap(queryString));
        }
        String currPage = request.getParameter("currentPage");
        currentPage = StringUtil.isEmpty(currPage) ? 1 : Integer.valueOf(currPage);
        p.setPage(currentPage);
        p.setService(commonService);
        List<K> list = p.execute();
        model.addAttribute("list", list);
        model.addAttribute("command", p);
        model.addAttribute("count", p.executeCount());
        model.addAttribute("pagination", new Pagination());
        model.addAttribute("currentPage",currentPage);
        return templates(INDEX);
    }

    @RequestMapping("/{id}")
    public String load(Model model, @PathVariable("id") Integer id) {
        T t = commonService.load(getClazz(), id);
        model.addAttribute("model", t);
        return templates(SHOW);
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String createForm(T t, Model model) {
        model.addAttribute("model", t);
        model.addAttribute("action", "create");
        return templates(FORM);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid T t) {
        commonService.create(t);
        return REDIRECT + baseUrl();
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Integer id, Model model) {
        T t = commonService.load(getClazz(),id);
        model.addAttribute("model", t);
        model.addAttribute("action", "update");
        return templates(FORM);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String edit(T t) {
        commonService.update(t);
        return REDIRECT + baseUrl();
    }


    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Integer id,RedirectAttributes redirectAttributes) {
        commonService.delete(commonService.load(getClazz(), id));
        redirectAttributes.addFlashAttribute(Consts.MESSAGE, Message.SUCCESS);
        return REDIRECT + baseUrl();
    }

    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

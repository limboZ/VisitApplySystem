package cn.com.scal.website.action.filter;

import cn.com.scal.components.dto.Api;
import cn.com.scal.components.utils.ErrorMessage;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vslimit on 15/3/19.
 */
public class SQLFilter extends HttpServlet implements Filter {

    private String injStr = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|drop|; |or|-|+|,";
    protected FilterConfig filterConfig = null;
    /**
     * Should a character encoding specified by the client be ignored?
     */
    protected boolean ignore = true;

    public void init(FilterConfig config) throws ServletException {
        this.filterConfig = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();

        if (requestURI.contains("/assets") || requestURI.startsWith("/res") ) {
            chain.doFilter(request, response);
            return;
        }
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        for (Object o : request.getParameterMap().values()) {
            String[] value = (String[]) o;
            for (String aValue : value) {
                if (sql_inj(aValue)) {
                    out.write(String.format("{\"code\":\"-1\",\"tip\":\"%s\"}", ErrorMessage.SQL_CHARACT));
                    return;
                }
            }
        }
        response.reset();
        chain.doFilter(request, response);

    }

    public boolean sql_inj(String str) {
        String[] injStrArray = injStr.split("\\|");
        for (String inj : injStrArray) {
            if (str.contains(inj + " ")) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
    }

}

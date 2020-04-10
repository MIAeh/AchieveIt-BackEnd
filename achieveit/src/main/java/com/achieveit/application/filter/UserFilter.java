package com.achieveit.application.filter;

import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Filter for User
 * @author Alevery, Felix
 */
@WebFilter(filterName = "userFilter", urlPatterns = {"/*"})
public class UserFilter extends BaseFilter {
    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(UserFilter.class);

    /**
     * Avoid using magic value
     */
    private static final String IS_LOGIN = "isLogin";
    private static final String TRUE = "true";

    UserFilter() {
        super(new ArrayList<>(Arrays.asList("/user/login", "/user/register", "/user/islogin", "/user/logout")));
    }

    /**
     * 对用户请求进行过滤，需要用户登录才能执行操作
     * @param servletRequest servlet请求
     * @param servletResponse servlet恢复
     * @param filterChain 过滤链
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        HttpServletResponse response = initHttpServletResponse(servletResponse);
        String uri = request.getRequestURI();

        if (!isNeedFilter(uri)) {
            filterChain.doFilter(request, response);
        } else {
            if (session != null && session.getAttribute(IS_LOGIN) != null && TRUE.equals(session.getAttribute(IS_LOGIN))) {
                filterChain.doFilter(request, response);
            } else {
                logger.info("access deny");
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error(400, "Should Login!")).toString());
                response.setContentType("application/json;charset=UTF-8");
            }
        }
    }
}
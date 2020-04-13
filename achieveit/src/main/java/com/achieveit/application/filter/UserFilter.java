package com.achieveit.application.filter;

import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.wrapper.ResultGenerator;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Filter for User
 *
 * @author Alevery, Felix
 */
@Order(1)
@WebFilter(filterName = "userFilter", urlPatterns = {"/*"})
public class UserFilter extends BaseFilter {
    /**
     * Avoid using magic value
     */
    private static final String IS_LOGIN = "isLogin";
    private static final String TRUE = "true";
    /**
     * Logger
     */
    private final Logger logger = LoggerFactory.getLogger(UserFilter.class);

    public UserFilter() {
        super(new ArrayList<>(Arrays.asList("/achieveit-backend/user/userLoginByID", "/achieveit-backend/user/userLoginById", "/user/userLoginByID", "/achieveit-backend/user/userLoginByPhone", "/achieveit-backend/user/userLoginByMail", "/user/userLoginByMail",
                "/achieveit-backend/user/register", "/achieveit-backend/user/isLogin", "/achieveit-backend/user/logout", "/user/register", "/user/isLogin")));
    }

    /**
     * 对用户请求进行过滤，需要用户登录才能执行操作
     *
     * @param servletRequest  servlet请求
     * @param servletResponse servlet恢复
     * @param filterChain     过滤链
     * @throws IOException      IOException
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
                if (session != null && session.getAttribute(IS_LOGIN) != null) {
                    logger.info("No login!");
                }
                logger.info("Access Deny,Please Login First!");
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error(ErrorCode.UNAUTHORIZED)).toString());
                response.setContentType("application/json;charset=UTF-8");
            }
        }
    }
}
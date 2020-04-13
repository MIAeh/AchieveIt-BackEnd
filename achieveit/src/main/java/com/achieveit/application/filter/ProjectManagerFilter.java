package com.achieveit.application.filter;

import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.enums.UserRoles;
import com.achieveit.application.wrapper.ResultGenerator;
import net.sf.json.JSONObject;
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

@Order(2)
@WebFilter(
        filterName = "projectManagerFilter",
        urlPatterns = {
                "/project/createProjectByID",
                "/project/updateProjectByID",
                "/project/addMemberByID",
                "/project/addMemberRoleByID",
                "/project/removeMemberRoleByID",
                "/project/updateMemberSuperiorByID",
                "/project/deleteMemberByID",
                "/project/updateGitRepoByID",
                "/authority/addGitMembersByID",
                "/authority/deleteGitMemberByID",
                "/authority/addMailMembersByID",
                "/authority/deleteMailMemberByID",
                "/authority/deleteFileMemberByID",
                // TODO: 风险管理
                // TODO: 功能列表
                "/status/launchProject",
                "/status/deliverProject",
                "/status/endProject",
                "/status/updateArchive"})
public class ProjectManagerFilter extends BaseFilter {
    public ProjectManagerFilter() {
        super();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        HttpServletResponse response = initHttpServletResponse(servletResponse);
        String uri = request.getRequestURI();

        if (!isNeedFilter(uri)) {
            filterChain.doFilter(request, response);
        } else {
            if (session != null && session.getAttribute("userRole") != null && UserRoles.PROJECT_MANAGER.getRole().equals(session.getAttribute("userRole"))) {
                filterChain.doFilter(request, response);
            } else {
                response.getWriter().write(JSONObject.fromObject(ResultGenerator.error(ErrorCode.ROLE_ERROR)).toString());
                response.setContentType("application/json;charset=UTF-8");
            }
        }
    }
}
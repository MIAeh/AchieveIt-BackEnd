package com.achieveit.application.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.ArrayList;

@Order(2)
@WebFilter(filterName = "userFilter", urlPatterns = {"/project/"})
public class ProjectFilter extends BaseFilter {
    public ProjectFilter(){ super(new ArrayList<String>());}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter(servletRequest,servletResponse,filterChain);
    }
}
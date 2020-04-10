package com.achieveit.application.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Base Filter
 * @author Alevery, Felix
 */
public abstract class BaseFilter implements Filter {

    /**
     * 需要进行过滤的URL
     */
    protected List<String> includeUrls;

    public BaseFilter() {
    }

    BaseFilter(List<String> includeUrls) {
        this.includeUrls = includeUrls;
    }

    public void init(FilterConfig var1) throws ServletException{

    }

    public void destroy(){

    }

    /**
     * 避免调试时报错，对HttpServletResponse进行初始化
     * @param servletResponse 要初始化的servletResponse
     * @return 初始化后的HttpServletResponse
     */
    protected HttpServletResponse initHttpServletResponse(ServletResponse servletResponse) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        return response;
    }

    /**
     * 判断请求的URL是否是需要过滤的URL
     * @param uri 请求的URL
     * @return isNeedFilter
     */
    protected boolean isNeedFilter(String uri) {
        return this.includeUrls == null || !this.includeUrls.contains(uri);
    }
}

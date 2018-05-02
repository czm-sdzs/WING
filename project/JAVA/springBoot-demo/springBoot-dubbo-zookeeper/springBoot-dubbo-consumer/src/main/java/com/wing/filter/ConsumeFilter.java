package com.wing.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Create By: CuiBo
 * Date: 2018/3/15 15:25
 * Description:
 */
@WebFilter(urlPatterns = "/*")
public class ConsumeFilter implements Filter {


    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        ServletRequest requestWrapper = null;
        if(request instanceof HttpServletRequest) {
            requestWrapper = new AppServletRequestWrapper((HttpServletRequest) request);
        }
        if(requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }
}

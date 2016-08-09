package ua.epam.web.filter;

import ua.epam.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lomak on 19.01.2016.
 */
public class PageRedirectSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }


}
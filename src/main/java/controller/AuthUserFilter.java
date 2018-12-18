package controller;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class AuthUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(true);
        User user = (User) session.getAttribute("Logger_user");
        if(user == null && !httpServletRequest.getRequestURI().equals("/AuthUserServlet") && !httpServletRequest.getRequestURI().equals("/")){
            httpServletResponse.sendRedirect("/");
        } else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}

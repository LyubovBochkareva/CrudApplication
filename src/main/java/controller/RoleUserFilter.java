package controller;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/home")
public class RoleUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(true);

        User user = (User) session.getAttribute("Logger_user");
        if (user.getRole().equals("user")) {
            session.setAttribute("role", user.getRole());
            httpServletResponse.sendRedirect("/user");
        } else {
            session.setAttribute("role", user.getRole());
            httpServletResponse.sendRedirect("/admin");
        }

    }

    @Override
    public void destroy() {

    }
}

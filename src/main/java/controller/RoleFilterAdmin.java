package controller;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class RoleFilterAdmin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);


        //Существует ли сессиия
        boolean loggedIn = session != null && session.getAttribute("Logger_user") != null;
        if (loggedIn) {
            User user = (User) session.getAttribute("Logger_user");
            String userRole = user.getRole();
            switch (userRole) {
                case "user":
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user");
                    break;
                case "admin":
                    try {
                        chain.doFilter(request, response);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
                    //Если нет то на страницу входа.
                    break;
                default:
                    httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
                    break;
            }
        }
    }

    @Override
    public void destroy() {

    }
}

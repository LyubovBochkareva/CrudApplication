package controller;

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
        boolean loggedIn = session != null && session.getAttribute("role") != null;
        if (loggedIn ){
            //Если существует то получаем роль
            String userRole = session.getAttribute("role").toString();
            if (userRole.equals("user")){
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user");
            }
            //Если нет то на страницу входа.
        }else httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "index.jsp");
    }

     /*   User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user != null && user.getRole().equals("Admin")){
            chain.doFilter(request,response);
        } else {
            httpServletResponse.sendRedirect("accessDenied.jsp");
        }
    }*/

    @Override
    public void destroy() {

    }
}

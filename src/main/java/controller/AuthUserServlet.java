package controller;

import model.User;
import service.UserService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/AuthUserServlet")
public class AuthUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setContentType("text/html;charset=utf-8");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            HttpSession session = req.getSession();
            if (login == null || password == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }


            List<User> userList = userService.getUserByLoginPassword(login,password);

            if (userList.isEmpty()) {
                resp.sendRedirect("/"); //index.jsp
            } else {
                User user = userList.get(0);

                if (user.getRole().equals("user")) {
                    session.setAttribute("role", user.getRole());
                    resp.sendRedirect("/user");
                } else if(user.getRole().equals("admin")){
                    session.setAttribute("role", user.getRole());
                    resp.sendRedirect("/admin");
                }
            }
        }
}

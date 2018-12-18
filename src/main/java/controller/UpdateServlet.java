package controller;

import service.UserService;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
            req.setAttribute("getUserById", userService.getUser(id));
            req.getRequestDispatcher("/updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    //    Long id = Long.valueOf(req.getParameter("id"));
      //      User user = (userService.getUser(id));
        User user = new User();
        user.setId(Long.valueOf(req.getParameter("id")));
            user.setLogin(req.getParameter("login"));
            user.setPassword(req.getParameter("password"));
            user.setName(req.getParameter("name"));
            user.setAge(Integer.parseInt(req.getParameter("age")));
            user.setRole(req.getParameter("role"));
            userService.updateUser(user);
            resp.sendRedirect("/admin");
    }
}

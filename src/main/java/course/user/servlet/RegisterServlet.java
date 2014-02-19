package course.user.servlet;

import course.user.dao.CrudUserDao;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String successmessage = "Perfect, now login!!";
        String errormessage = "Something is wrong";

        PasswordService passwordService = new DefaultPasswordService();
        String encryptedPassword = passwordService.encryptPassword(password);
        String sql = "insert into user(username, password) values(?,?);";
        CrudUserDao userDao = new CrudUserDao();
        boolean cu = userDao.createUser(sql, username, encryptedPassword);
        if (cu == true) {

            response.setHeader("perfect", successmessage);
            response.sendRedirect("login");
        } else {
            response.sendError(100, "fehler");
            //response.setHeader("error", errormessage);
            //response.sendRedirect("register");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
}

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
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String description = request.getParameter("description");

        String successmessage = "passt, now login with your credentials";
        String errormessage = "maybe you're not the first, who likes this username";

        PasswordService passwordService = new DefaultPasswordService();
        String encryptedPassword = passwordService.encryptPassword(password);
        //String sql = "insert into user(username, password) values(?,?);";
        CrudUserDao userDao = new CrudUserDao();
        boolean cu = userDao.createUser(username, encryptedPassword, description);
        if (cu == true) {
            request.setAttribute("success", successmessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request,response);
            //response.sendRedirect("login");
        } else {
            //request.setAttribute("error", errormessage);
            //RequestDispatcher dispatcher = request.getRequestDispatcher("index");
            //dispatcher.forward(request,response);
            PrintWriter out = response.getWriter();
            out.println("<font color=red>it is wrong</font>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
}

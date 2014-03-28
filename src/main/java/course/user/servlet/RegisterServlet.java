package course.user.servlet;

import course.contact.dao.CrudContactDao;
import course.dataaccess.MysqlDaoFactory;
import course.user.dao.UserDao;
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

        //CrudUserDao userDao = new CrudUserDao();
        CrudContactDao contactDao = new CrudContactDao();

        //String sql = "insert into user(username, password) values(?,?);";
        UserDao userDao = MysqlDaoFactory.getInstance().getUserDao();

        boolean cu = userDao.createUser(username, encryptedPassword, description);
        if (cu == true) {
            int uId = userDao.getUserId(username);
            boolean c = contactDao.createContact(uId, uId);
            if (c == true) {
                request.setAttribute("success", successmessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("login");
                dispatcher.forward(request,response);
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>it is wrong</font>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
}

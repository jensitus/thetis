package course.user.servlet;

import course.user.dao.CrudUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String sql = "select * from user where username = ?;";

        CrudUserDao crudUserDao = new CrudUserDao();
        String username;
        String errormessage = null;
        username = crudUserDao.readUser(sql, user, pass);

        if (username != null){
            request.setAttribute("username", username);
        } else if (username == null){
            request.setAttribute("errormessage", errormessage);
        }

        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();
        httpSession.setAttribute("user", username);

        Cookie cookie = new Cookie(username, sessionId);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        response.sendRedirect("index");

        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        //requestDispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }
}

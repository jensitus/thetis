package course.user.servlet;

import course.user.dao.CrudUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();

        CrudUserDao crudUserDao = new CrudUserDao();
        String username;
        username = crudUserDao.loginUser(user, pass);

        if (username == null) {
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either username or password is wrong</font>");
        } else if (username.equals(user)){
            httpSession.setAttribute("user", username);
            Cookie cookie = new Cookie(username, sessionId);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            response.sendRedirect("connected_posts");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }
}

package course.user.servlet;

import course.user.dao.CrudUserDao;
import course.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/*")
public class ShowUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");

        CrudUserDao crudUserDao = new CrudUserDao();

        User user;
        user = crudUserDao.readUser(userName);

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../show_user.jsp");
        dispatcher.forward(request, response);

    }



}

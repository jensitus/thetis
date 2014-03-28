package course.user.servlet;

import course.dataaccess.MysqlDaoFactory;
import course.user.dao.UserDao;
import course.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_user/*")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String description = request.getParameter("description");

        UserDao crudUserDao =  MysqlDaoFactory.getInstance().getUserDao();
        crudUserDao.updateUser(username, description);

        response.sendRedirect("/thetis-1/user/?name=" + username);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        UserDao crudUserDao =  MysqlDaoFactory.getInstance().getUserDao();

        System.out.println(name);

        User user;
        user = crudUserDao.readUser(name);

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("../update_user.jsp");
        requestDispatcher.forward(request, response);

    }
}

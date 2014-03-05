package course.user.servlet;

import course.contact.dao.CrudContactDao;
import course.post.dao.CrudPostDao;
import course.post.model.Post;
import course.user.dao.CrudUserDao;
import course.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/*")
public class ShowUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");

        String uvariable = (String) request.getAttribute("u");

        CrudUserDao crudUserDao = new CrudUserDao();
        CrudContactDao contactDao = new CrudContactDao();
        CrudPostDao postDao = new CrudPostDao();

        List<Post> postByUser = postDao.postByUser(userName);

        User user;
        user = crudUserDao.readUser(userName);

        int userReaderId = crudUserDao.getUserId(uvariable);
        int userToReadId = crudUserDao.getUserId(userName);

        int countedPosts = postDao.countPosts(userToReadId);

        boolean c = contactDao.readConnectedUsers(userReaderId, userToReadId);

        List<User> connectedUser;
        connectedUser = contactDao.connectedUserList(userToReadId);

        System.out.println(connectedUser);

        request.setAttribute("connectedWith", connectedUser);
        request.setAttribute("posts", postByUser);
        request.setAttribute("cp", countedPosts);
        request.setAttribute("c", c);
        request.setAttribute("uvariable", uvariable);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../show_user.jsp");
        dispatcher.forward(request, response);

    }

}

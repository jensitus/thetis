package course.user.servlet;

import course.contact.dao.ContactDao;
import course.dataaccess.MysqlDaoFactory;
import course.post.dao.PostDao;
import course.post.model.Post;
import course.user.dao.UserDao;
import course.user.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class ShowUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("name");

        String uvariable = (String) request.getAttribute("u");

        UserDao crudUserDao =  MysqlDaoFactory.getInstance().getUserDao();
        ContactDao contactDao = MysqlDaoFactory.getInstance().getContactDao();
        PostDao postDao = MysqlDaoFactory.getInstance().getPostDao();

        List<Post> postByUser = postDao.postByUser(userName);

        User user;
        user = crudUserDao.readUser(userName);

        int userReaderId = crudUserDao.getUserId(uvariable);
        int userToReadId = crudUserDao.getUserId(userName);

        int countedPosts = postDao.countPosts(userToReadId);

        boolean c = contactDao.readConnectedUsers(userReaderId, userToReadId);

        List<String> connectedWith;
        connectedWith = contactDao.connectedWith(userToReadId);

        StringBuilder connWith = new StringBuilder();
        for ( String cw : connectedWith ) {
            cw = "<a href=" + "\"../user/?name=" + cw + "\">" + cw + "</a>";
            if (connWith.length() > 0) {
                connWith.append(", ");
            }
            connWith.append(cw);
        }



        List<String> connectedBy;
        connectedBy = contactDao.connectedBy(userToReadId);

        StringBuilder conBy = new StringBuilder();
        for (String cb : connectedBy) {
            cb = "<a href=" + "\"../user/?name=" + cb + "\">" + cb + "</a>";
            if (conBy.length() > 0) {
                conBy.append(", ");
            }
            conBy.append(cb);
        }

        request.setAttribute("connectedBy", conBy);
        request.setAttribute("connectedWith", connWith);
        request.setAttribute("posts", postByUser);
        request.setAttribute("cp", countedPosts);
        request.setAttribute("c", c);
        request.setAttribute("uvariable", uvariable);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../show_user.jsp");
        dispatcher.forward(request, response);

    }

}

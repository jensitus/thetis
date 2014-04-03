package course.post.servlet;

import course.dataaccess.MysqlDaoFactory;
import course.post.dao.PostDao;
import course.post.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/posts/*")
public class PostsByUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String user = request.getParameter("user");
        PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();
        List<Post> posts = crudPostDao.postByUser(user);

        request.setAttribute("posts", posts);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("../posts_by_user.jsp");
        requestDispatcher.forward(request, response);

    }
}

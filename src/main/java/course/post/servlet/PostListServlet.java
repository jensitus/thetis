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

@WebServlet("/posts")
public class PostListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doGet(request, response);

        PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();
        List<Post> posts = crudPostDao.postList();

        //System.out.print(posts);

        //Post posts = (Post) crudPostDao.postList();

        request.setAttribute("posts", posts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("posts.jsp");
        dispatcher.forward(request, response);

    }
}

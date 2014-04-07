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

@WebServlet("/post/*")
public class ShowSinglePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);
        List<Integer> answeringPostId;
        List<Post> answeringPosts;

        Post post;
        PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();
        post = crudPostDao.readPost(id1);

        if (post == null) {
            response.sendRedirect("/index");
        } else {

            int answeredPostId = crudPostDao.answeredPostId(id1);
            Post answeredPost = crudPostDao.readPost(answeredPostId);

            answeringPostId = crudPostDao.getAnsweringPostId(id1);
            answeringPosts = crudPostDao.answeringPosts(answeringPostId);

            request.setAttribute("answeredPost", answeredPost);
            request.setAttribute("answers", answeringPosts);
            request.setAttribute("post", post);
            RequestDispatcher dispatcher = request.getRequestDispatcher("../singlePost.jsp");
            dispatcher.forward(request, response);
        }
    }
}

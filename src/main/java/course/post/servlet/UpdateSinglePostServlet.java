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

@WebServlet("/updatePost/*")
public class UpdateSinglePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String aidee = request.getParameter("id");
        int id1 = Integer.parseInt(aidee);

        PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();
        Post post;

        post = crudPostDao.readPost(id1);

        request.setAttribute("post", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../updatePost.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String aidee = request.getParameter("id");
        int id1 = Integer.parseInt(aidee);
        String title = request.getParameter("title");
        String body = request.getParameter("body");

        PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();

        crudPostDao.updatePost(id1, title, body);

        response.sendRedirect("/post/?id=" + id1);

    }
}

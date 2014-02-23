package course.post.servlet;

import course.post.dao.CrudPostDao;
import course.post.model.Post;
import course.servlet.IndexServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("post/*")
public class ShowSinglePostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        int id1 = Integer.parseInt(id);

        Post post;
        CrudPostDao crudPostDao = new CrudPostDao();
        post = crudPostDao.readPost(id1);

        request.setAttribute("post", post);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../singlePost.jsp");
        dispatcher.forward(request, response);

    }
}

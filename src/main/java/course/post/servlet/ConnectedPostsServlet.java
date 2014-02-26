package course.post.servlet;

import course.post.dao.CrudPostDao;
import course.post.model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/connected_posts")
public class ConnectedPostsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String u = (String) request.getAttribute("u");

        if (u.equals(user)) {
            CrudPostDao postDao = new CrudPostDao();
            List<Post> contactPosts = postDao.contactPosts(user);
            request.setAttribute("contactPosts", contactPosts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("connected_posts.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index");
        }

    }
}

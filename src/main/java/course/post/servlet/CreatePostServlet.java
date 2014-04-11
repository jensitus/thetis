package course.post.servlet;

import course.dataaccess.MysqlDaoFactory;
import course.post.dao.PostDao;
import course.user.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = { "/create", "/create/*" })
public class CreatePostServlet extends HttpServlet {
    private UserDao crudUserDao = MysqlDaoFactory.getInstance().getUserDao();
    private PostDao crudPostDao = MysqlDaoFactory.getInstance().getPostDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String answeredPostIdString = request.getParameter("answeredPostId");
        int answeredPostId = 0;
        if (answeredPostIdString != null){
            answeredPostId = Integer.parseInt(answeredPostIdString);
        }
        System.out.println(answeredPostId);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        int userId;

        if (title.trim().isEmpty()) {
            String error = "Come on, it isn't that hard to fill in a title correctly in this motherfucking form:";
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
            dispatcher.forward(request,response);
        } else {
            userId = crudUserDao.getUserId(username);
            crudPostDao.createPost(title, body, userId, answeredPostId);
            response.sendRedirect("/user/?name=" + username);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String answeredPostId = request.getParameter("answeredPostId");

        request.setAttribute("title", title);
        request.setAttribute("body", body);
        request.setAttribute("answeredPostId", answeredPostId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
        dispatcher.forward(request, response);
    }
}

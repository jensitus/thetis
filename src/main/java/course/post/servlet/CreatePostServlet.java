package course.post.servlet;

import course.post.dao.CrudPostDao;
import course.user.dao.CrudUserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/create")
public class CreatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String body = request.getParameter("body");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        int userId;

        String testTitle = title;
        String testBody = body;

        if (title.trim().isEmpty()) {
            String error = "So nicht!!";
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
            dispatcher.forward(request,response);
        } else {

            CrudUserDao crudUserDao = new CrudUserDao();
            userId = crudUserDao.getUserId(username);

            CrudPostDao crudPostDao = new CrudPostDao();
            crudPostDao.createPost(title, body, userId);

            response.sendRedirect("/user/?name=" + username);

        }

        //RequestDispatcher dispatcher = request.getRequestDispatcher("");
        //dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("createPost.jsp");
        dispatcher.forward(request, response);
    }
}

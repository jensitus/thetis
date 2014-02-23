package course.post.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/stringtest")
public class StringTServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("stringtest.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String body = req.getParameter("body");

        String error = "empty";

        title = title.trim();

        if (title.isEmpty()) {
            req.setAttribute("title", error);
        } else {


            req.setAttribute("title", title);
            req.setAttribute("body", body);
            //req.setAttribute("b", b);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("stringtest.jsp");
        dispatcher.forward(req,resp);

    }
}

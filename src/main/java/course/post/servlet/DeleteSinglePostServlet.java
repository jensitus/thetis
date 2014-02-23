package course.post.servlet;

import course.post.dao.CrudPostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deletePost")
public class DeleteSinglePostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);

        String aidee = req.getParameter("id");
        //req.getAttribute("u");
        int id = Integer.parseInt(aidee);

        CrudPostDao crudPostDao = new CrudPostDao();
        crudPostDao.deletePost(id);

        resp.sendRedirect("posts");
    }
}

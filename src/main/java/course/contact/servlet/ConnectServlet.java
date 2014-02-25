package course.contact.servlet;

import course.contact.dao.CrudContactDao;
import course.user.dao.CrudUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/connect_user")
public class ConnectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reader = request.getParameter("reader");
        String toRead = request.getParameter("toRead");

        String u_variable = (String) request.getAttribute("u");



        int readerId;
        int toReadId;
        CrudUserDao crudUserDao = new CrudUserDao();

        int uvId = crudUserDao.getUserId(u_variable);
        readerId = crudUserDao.getUserId(reader);
        toReadId = crudUserDao.getUserId(toRead);

        CrudContactDao contactDao = new CrudContactDao();
        boolean c = contactDao.createContact(readerId, toReadId);
        if (c == true) {
            PrintWriter out = response.getWriter();
            out.println(reader + ": ");
            out.println(readerId);
            out.println("is now connected with: ");
            out.println(toRead + " " + toReadId);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

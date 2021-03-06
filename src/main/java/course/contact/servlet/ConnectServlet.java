package course.contact.servlet;

import course.contact.dao.ContactDao;
import course.dataaccess.MysqlDaoFactory;
import course.user.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connect_user")
public class ConnectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reader = request.getParameter("reader");
        String toRead = request.getParameter("toRead");

        //String u_variable = (String) request.getAttribute("u");

        int readerId;
        int toReadId;
        UserDao crudUserDao = MysqlDaoFactory.getInstance().getUserDao();

        //int uvId = crudUserDao.getUserId(u_variable);
        readerId = crudUserDao.getUserId(reader);
        toReadId = crudUserDao.getUserId(toRead);

        ContactDao contactDao = MysqlDaoFactory.getInstance().getContactDao();
        boolean c = contactDao.createContact(readerId, toReadId);
        if (c == true) {
            String succ = "You got the connection you wanted";
            request.setAttribute("succ", succ);
            response.sendRedirect("user/?name=" + toRead);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

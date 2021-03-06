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

@WebServlet("/disconnect_user")
public class DisconnectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reader = request.getParameter("reader");
        String toRead = request.getParameter("toRead");

        UserDao crudUserDao =  MysqlDaoFactory.getInstance().getUserDao();

        int readerId = crudUserDao.getUserId(reader);
        int toReadId = crudUserDao.getUserId(toRead);

        ContactDao contactDao = MysqlDaoFactory.getInstance().getContactDao();
        boolean c = contactDao.deleteContact(readerId, toReadId);
        if (c == true) {
            response.sendRedirect("/user/?name=" + toRead);
        } else {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

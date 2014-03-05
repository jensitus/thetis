package course.contact.dao;

import course.dataaccess.BaseDao;
import course.user.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jens on 2/25/14.
 */
public class CrudContactDao extends BaseDao implements ContactDao {

    @Override
    public boolean createContact(int readerId, int toReadId) {
        PreparedStatement preparedStatement;
        boolean t = false;
        preparedStatement = getPreparedStatement("insert into reader(readerId, toReadId) values(?,?);");
        try {
            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, toReadId);
            preparedStatement.executeUpdate();
            t = true;
        } catch (SQLException e) {
            e.printStackTrace();
            t = false;
        } finally {
            closeConn();
        }
        return t;
    }

    @Override
    public boolean readConnectedUsers(int userReaderId, int userToReadId) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int eins = 0;
        int zwei = 0;

        preparedStatement = getPreparedStatement("select * " +
                "from reader " +
                "where readerId = ? " +
                "and toReadId = ?;");
        try {
            preparedStatement.setInt(1, userReaderId);
            preparedStatement.setInt(2, userToReadId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eins = resultSet.getInt("readerId");
                zwei = resultSet.getInt("toReadId");
                System.out.println(eins);
                System.out.println(zwei);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

        if (eins == userReaderId & zwei == userToReadId) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteContact(int readerId, int toReadId) {
        PreparedStatement preparedStatement;
        boolean dc = false;
        preparedStatement = getPreparedStatement("delete from reader " +
                "where readerId = ? and toReadId = ?;");
        try {
            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, toReadId);
            preparedStatement.execute();
            dc = true;

        } catch (SQLException e) {
            e.printStackTrace();
            dc = false;
        } finally {
            closeConn();
        }
        System.out.println("dc: ");
        System.out.println(dc);
        return dc;
    }

    @Override
    public List<User> connectedUserList(int userReaderId) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<User> cU = new ArrayList<>();

        preparedStatement = getPreparedStatement("select user.username, user.id from user, reader where toReadId = user.id and readerId = ?;");
        try {
            preparedStatement.setInt(1, userReaderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                User user = new User(username);
                cU.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(cU);
        return cU;
    }

}

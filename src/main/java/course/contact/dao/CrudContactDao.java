package course.contact.dao;

import course.dataaccess.NewBaseDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jens on 2/25/14.
 */
public class CrudContactDao extends NewBaseDao implements ContactDao {

    public CrudContactDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public boolean createContact(int readerId, int toReadId) {
        PreparedStatement preparedStatement;
        boolean t = false;
        Connection con = null;

        try{
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "insert into reader(readerId, toReadId) values(?,?);");
            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, toReadId);
            preparedStatement.executeUpdate();
            t = true;
        } catch (SQLException e) {
            e.printStackTrace();
            t = false;
        } finally {
            closeConn(con);
        }
        return t;
    }

    @Override
    public boolean readConnectedUsers(int userReaderId, int userToReadId) {
        Connection con = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        int eins = 0;
        int zwei = 0;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select * " +
                "from reader " +
                "where readerId = ? " +
                "and toReadId = ?;");
            preparedStatement.setInt(1, userReaderId);
            preparedStatement.setInt(2, userToReadId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eins = resultSet.getInt("readerId");
                zwei = resultSet.getInt("toReadId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
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
        Connection con = null;
        boolean dc = false;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"delete from reader " +
                "where readerId = ? and toReadId = ?;");

            preparedStatement.setInt(1, readerId);
            preparedStatement.setInt(2, toReadId);
            preparedStatement.execute();
            dc = true;

        } catch (SQLException e) {
            e.printStackTrace();
            dc = false;
        } finally {
            closeConn(con);
        }
        return dc;
    }

    @Override
    public List<String> connectedWith(int userReaderId) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;
        List<String> cU = new ArrayList<>();

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select user.username, user.id " +
                "from user, reader " +
                "where toReadId = user.id " +
                "and readerId = ? " +
                "and readerId != toReadId;");
            preparedStatement.setInt(1, userReaderId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                cU.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return cU;
    }

    @Override
    public List<String> connectedBy(int userToReadId) {
        PreparedStatement preparedStatement;
        Connection con = null;
        ResultSet resultSet;
        List<String> cB = new ArrayList<>();

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"select user.username, user.id " +
                "from user, reader " +
                "where readerId = user.id " +
                "and toReadId = ? " +
                "and readerId != toReadId;");
            preparedStatement.setInt(1, userToReadId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                cB.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return cB;
    }

}

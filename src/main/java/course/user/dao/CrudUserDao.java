package course.user.dao;

import course.dataaccess.NewBaseDao;
import course.user.model.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUserDao extends NewBaseDao implements UserDao {


    public CrudUserDao(final javax.sql.DataSource dataSource)
    {
      super(dataSource);
    }

  @Override
    public boolean createUser(String username, String encryptedPassword, String description) {

        PreparedStatement preparedStatement = null;
        boolean r;
        Connection con = null;
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"insert into user(username, password, description) values(?,?,?);");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
            r = true;
        } catch (SQLException e) {
            System.out.println("doppelter user: ");
            System.out.println(e.toString()); //.printStackTrace();
            r = false;
        } finally {
            closeConn(con);
        }
        return r;

    }

    @Override
    public String loginUser(String user, String pass) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        PasswordService passwordService = new DefaultPasswordService();
        String username = null;
        String password = null;
        Connection con = null;
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"select * from user where username = ?;");
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        if (passwordService.passwordsMatch(pass, password)){
            return username;
        } else {
            return null;
        }
    }

    @Override
    public int getUserId(String username) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int userId = 0;
        Connection con = null;
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select * from user where username = ?;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

        return userId;
    }

    @Override
    public User readUser(String userName) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        User user = null;
        int id;
        String username;
        String description;
        Connection con = null;
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"select * from user where username = ?;");
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                username = resultSet.getString("username");
                description = resultSet.getString("description");
                user = new User(id, username, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

        System.out.println(user);

        return user;
    }

    @Override
    public void updateUser(String username, String description) {
        PreparedStatement preparedStatement;
        Connection con = null;
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con,"update user " +
              "set description = ? where username = ?;");
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConn(con);
        }

    }
}

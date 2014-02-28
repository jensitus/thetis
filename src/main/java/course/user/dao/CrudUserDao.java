package course.user.dao;

import course.dataaccess.BaseDao;
import course.user.model.User;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUserDao extends BaseDao implements UserDao {

    @Override
    public boolean createUser(String username, String encryptedPassword, String description) {

        PreparedStatement preparedStatement;
        boolean r;

        preparedStatement = getPreparedStatement("insert into user(username, password, description) values(?,?,?);");
        try {
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
            closeConn();
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
        preparedStatement = getPreparedStatement("select * from user where username = ?;");
        try {
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
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

        preparedStatement = getPreparedStatement("select * from user where username = ?;");
        try {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
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
        preparedStatement = getPreparedStatement("select * from user where username = ?;");
        try {
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
            closeConn();
        }

        System.out.println(user);

        return user;
    }

    @Override
    public void updateUser(String username, String description) {
        PreparedStatement preparedStatement;

        preparedStatement = getPreparedStatement("update user " +
                "set description = ? where username = ?;");
        try {
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            closeConn();
        }

    }
}

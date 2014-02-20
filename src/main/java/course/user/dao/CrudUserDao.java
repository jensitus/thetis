package course.user.dao;

import course.dataaccess.BaseDao;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUserDao extends BaseDao implements UserDao {

    @Override
    public boolean createUser(String sql, String username, String encryptedPassword) {

        PreparedStatement preparedStatement;

        preparedStatement = getPreparedStatement(sql);
        try {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("doppelter user: ");
            System.out.println(e.toString()); //.printStackTrace();
            return false;
        } finally {
            closeConn();
        }

    }

    @Override
    public String readUser(String sql, String user, String pass) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        PasswordService passwordService = new DefaultPasswordService();
        String username = null;
        String password = null;
        preparedStatement = getPreparedStatement(sql);
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
}

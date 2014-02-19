package course.user.dao;

/**
 * Created by jens on 2/18/14.
 */
public interface UserDao {

    public boolean createUser(String sql, String username, String encryptedPassword);
    public String readUser(String sql, String user, String pass);

}

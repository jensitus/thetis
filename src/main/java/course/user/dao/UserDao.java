package course.user.dao;

/**
 * Created by jens on 2/18/14.
 */
public interface UserDao {

    public boolean createUser(String username, String encryptedPassword);
    public String loginUser(String user, String pass);
    public int getUserId(String username);
    public String readUser(String user);

}

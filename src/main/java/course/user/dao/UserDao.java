package course.user.dao;

import course.user.model.User;

/**
 * Created by jens on 2/18/14.
 */
public interface UserDao {

    public boolean createUser(String username, String encryptedPassword, String description);
    public String loginUser(String user, String pass);
    public int getUserId(String username);
    public User readUser(String user);
    public void updateUser(String username, String description);

}

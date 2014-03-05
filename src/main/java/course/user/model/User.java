package course.user.model;

/**
 * Created by jens on 2/17/14.
 */
public class User {

    private int id;
    private String username;
    private String description;

    public User(int id, String username, String description) {
        this.setId(id);
        this.setUsername(username);
        this.setDescription(description);
    }

    public User(String username) {
        this.setUsername(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

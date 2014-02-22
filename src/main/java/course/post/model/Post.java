package course.post.model;

public class Post {

    private int id;
    private String title;
    private String body;
    private int userId;
    private String username;

    public Post(int id, String title, String body, int userId, String username) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setUserId(userId);
        this.setUsername(username);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

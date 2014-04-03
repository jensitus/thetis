package course.post.model;

public class Post {

    private int id;
    private String title;
    private String body;
    private int userId;
    private String username;

  public Post(final int id, final String title, final String body, final int userId, final String username)
  {
    this.id = id;
    this.title = title;
    this.body = body;
    this.userId = userId;
    this.username = username;
  }

  public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }


    public int getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }

}

package course.post.dao;

import course.post.model.Post;

import java.util.List;

public interface PostDao {
    public void createPost(String title, String body, int userId);
    public Post readPost(int id);
    public void updatePost(int id, String title, String body);
    public void deletePost(int id);
    public List<Post> postList();
    public List<Post> postByUser(String user);
    public List<Post> contactPosts(String user);
}

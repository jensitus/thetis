package course.post.dao;

import course.post.model.Post;

public interface PostDao {
    public void createPost(String title, String body, int userId);
    public Post readPost();
    public void updatePost();
    public void deletePost();

}

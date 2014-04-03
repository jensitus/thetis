package course.post.dao;

import course.post.model.Post;

import java.util.List;

public interface PostDao {
    void createPost(String title, String body, int userId, int answeredPostId);
    Post readPost(int id);
    void updatePost(int id, String title, String body);
    void deletePost(int id);
    List<Post> postList();
    List<Post> postByUser(String user);
    List<Post> contactPosts(String user);
    int countPosts(int userId);
    List<Integer> getAnsweringPostId(int postId);
    List<Post> answeringPosts(List<Integer> answeringPostId);
    int answeredPostId (int postId);
}

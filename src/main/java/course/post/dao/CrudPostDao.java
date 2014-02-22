package course.post.dao;

import course.dataaccess.BaseDao;
import course.post.model.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CrudPostDao extends BaseDao implements PostDao {

    @Override
    public void createPost(String title, String body, int userId) {

        PreparedStatement preparedStatement;

        preparedStatement = getPreparedStatement("insert into post(title, body, userId) values(?,?,?);");
        try {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, body);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

    }

    @Override
    public Post readPost() {
        return null;
    }

    @Override
    public void updatePost() {

    }

    @Override
    public void deletePost() {

    }

    @Override
    public List<Post> postList() {

        List<Post> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        preparedStatement = getPreparedStatement("select * from post;");
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                int userId = resultSet.getInt("userId");
                Post post = new Post(id, title, body, userId);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return posts;
    }
}

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
    public Post readPost(int id1) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Post post = null;

        preparedStatement = getPreparedStatement("select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user on post.userId = user.id " +
                "where post.id = ?;");
        try {
            preparedStatement.setInt(1, id1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                post = new Post(id, title, body, userId, username);
                System.out.println(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return post;
    }

    @Override
    public void updatePost(int id, String title, String body) {
        PreparedStatement preparedStatement;

        preparedStatement = getPreparedStatement("update post set title = ?, body = ? " +
                "where id = ?;");
        try {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, body);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

    }

    @Override
    public void deletePost(int id) {
        PreparedStatement preparedStatement;

        preparedStatement = getPreparedStatement("delete from post where id = ?;");
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

    }

    @Override
    public List<Post> postList() {
        List<Post> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = getPreparedStatement("select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user " +
                "on post.userId = user.id " +
                "order by id desc;");
        try {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                Post post = new Post(id, title, body, userId, username);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return posts;
    }

    @Override
    public List<Post> postByUser(String user) {
        List<Post> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = getPreparedStatement("select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user " +
                "on post.userId = user.id " +
                "where user.username = ? " +
                "order by id desc;");
        try {
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                Post post = new Post(id, title, body, userId, username);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return posts;
    }

    @Override
    public List<Post> contactPosts(String user) {
        List<Post> conPosts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = getPreparedStatement("select " +
                "post.id, post.title, post.body, post.userId " +
                "from post, user, reader " +
                "where user.id = reader.readerId " +
                "and reader.toReadId = post.userId " +
                "and user.username = ? " +
                "order by id desc;");
        try {
            preparedStatement.setString(1, user);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("post.id");
                String title = resultSet.getString("post.title");
                String body = resultSet.getString("post.body");
                int userId = resultSet.getInt("post.userId");
                String username = getUsername(userId);
                Post post = new Post(id, title, body, userId, username);
                conPosts.add(post);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }
        return conPosts;
    }

    @Override
    public int countPosts(int userId) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        int cp = 0;
        System.out.println(userId);
        preparedStatement = getPreparedStatement("select count(*) as c from post where userId = ?;");
        try {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cp = resultSet.getInt(1);
                System.out.println(cp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cp;
    }


    private String getUsername(int userId){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String username = null;
        preparedStatement = getPreparedStatement("select * from user where id = ?;");
        try {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn();
        }

        return username;
    }


}

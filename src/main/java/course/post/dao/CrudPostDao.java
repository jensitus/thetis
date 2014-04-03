package course.post.dao;

import course.dataaccess.NewBaseDao;
import course.post.model.Post;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CrudPostDao extends NewBaseDao implements PostDao {

    public CrudPostDao(final DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void createPost(String title, String body, int userId, int answeredPostId) {

        Connection con = null;
        PreparedStatement preparedStatement;
        int last_inserted_id = 0;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "insert into post(title, body, userId) values(?,?,?);");
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, body);
            preparedStatement.setInt(3, userId);
            preparedStatement.executeUpdate();
            preparedStatement = getPreparedStatement(con, "select last_insert_id()");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                last_inserted_id = resultSet.getInt("last_insert_id()");
                System.out.print(last_inserted_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

        if (answeredPostId != 0) {
            articleRelation(answeredPostId, last_inserted_id);
        }

    }

    @Override
    public Post readPost(int id1) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Post post = null;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user on post.userId = user.id " +
                "where post.id = ?;");

            preparedStatement.setInt(1, id1);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                post = new Post(id, title, body, userId, username);
                //System.out.println(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return post;
    }

    @Override
    public void updatePost(int id, String title, String body) {
        PreparedStatement preparedStatement;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "update post set title = ?, body = ? " +
                "where id = ?;");

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, body);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

    }

    @Override
    public void deletePost(int id) {
        PreparedStatement preparedStatement;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "delete from post where id = ?;");

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

    }

    @Override
    public List<Post> postList() {
        List<Post> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user " +
                "on post.userId = user.id " +
                "order by id desc;");
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
            closeConn(con);
        }
        return posts;
    }

    @Override
    public List<Post> postByUser(String user) {
        List<Post> posts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select " +
                "post.id, post.title, post.body, post.userId, user.username " +
                "from post inner join user " +
                "on post.userId = user.id " +
                "where user.username = ? " +
                "order by id desc;");
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
            closeConn(con);
        }
        return posts;
    }

    @Override
    public List<Post> contactPosts(String user) {
        List<Post> conPosts = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select " +
                "post.id, post.title, post.body, post.userId " +
                "from post, user, reader " +
                "where user.id = reader.readerId " +
                "and reader.toReadId = post.userId " +
                "and user.username = ? " +
                "order by id desc;");
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
            closeConn(con);
        }
        return conPosts;
    }

    @Override
    public int countPosts(int userId) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;
        int cp = 0;
        //System.out.println(userId);
        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select count(*) as c from post where userId = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cp = resultSet.getInt(1);
                //System.out.println(cp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return cp;
    }


    private String getUsername(int userId){
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String username = null;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select * from user where id = ?;");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }

        return username;
    }

    private void articleRelation(int answeredPostId, int answeringPostId){
        PreparedStatement preparedStatement;
        Connection con = null;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "insert into " +
                    "post_relation(answeredPostId, answeringPostId) " +
                    "values(?,?);");
            preparedStatement.setInt(1, answeredPostId);
            preparedStatement.setInt(2, answeringPostId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
    }

    @Override
    public List<Integer> getAnsweringPostId(int postId) {
        PreparedStatement preparedStatement;
        Connection con = null;
        ResultSet resultSet;
        List<Integer> answeringPostId = new ArrayList<>();

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select answeringPostId from post_relation where answeredPostId = ?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int api = resultSet.getInt("answeringPostId");
                answeringPostId.add(api);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return answeringPostId;
    }

    @Override
    public List<Post> answeringPosts(List<Integer> aPostId) {

        List<Post> answeringPosts = new ArrayList<>();

        for ( Iterator<Integer> iter = aPostId.iterator(); iter.hasNext(); ) {
            int api = iter.next();
            Post post = readPost(api);
            answeringPosts.add(post);
        }
        return answeringPosts;
    }

    @Override
    public int answeredPostId(int postId) {

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection con = null;
        int answeredPostId = 0;

        try {
            con = getDataSource().getConnection();
            preparedStatement = getPreparedStatement(con, "select answeredPostId from post_relation where answeringPostId = ?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                answeredPostId = resultSet.getInt("answeredPostId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConn(con);
        }
        return answeredPostId;
    }


}

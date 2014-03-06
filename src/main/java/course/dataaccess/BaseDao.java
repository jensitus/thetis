package course.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jens on 2/17/14.
 */
public abstract class BaseDao extends DataSource {

    private Connection connection;

    protected BaseDao()
    {
      super();
    }

  protected Connection getDataSource() {
        connection = connectDb();
        return connection;
    }

    public PreparedStatement getPreparedStatement(String sql) {

        PreparedStatement preparedStatement = null;

        try {
            connection = (Connection) getDataSource();

            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;

    }

    public void closeConn() {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

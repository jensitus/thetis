package course.dataaccess;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

/**
 * Created by jens on 2/17/14.
 */
public abstract class DataSource {

    protected Connection connectDb() throws ClassNotFoundException {

        Connection connection = null;

        Properties properties = new Properties();
        try {
            properties.load(DataSource.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = properties.getProperty("jdbc.driver");
        String jdbcUrl = properties.getProperty("jdbc.url");
        String jdbcUser = properties.getProperty("jdbc.username");
        String jdbcPassword = properties.getProperty("jdbc.password");

        Class.forName(driver);
        try {
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}

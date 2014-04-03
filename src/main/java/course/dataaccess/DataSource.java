package course.dataaccess;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public abstract class DataSource {

  private Properties properties = new Properties();
  private String jdbcUrl;
  private String jdbcUser;
  private String jdbcPassword;

  protected DataSource()
  {
    setupDbDriver();
  }

    private void setupDbDriver()
        {
        try {
            properties.load(DataSource.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            String driver = properties.getProperty("jdbc.driver");
            jdbcUrl = properties.getProperty("jdbc.url");
            jdbcUser = properties.getProperty("jdbc.username");
            jdbcPassword = properties.getProperty("jdbc.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
}

  protected Connection connectDb() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

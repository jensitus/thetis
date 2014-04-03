package course.dataaccess;


import course.contact.dao.ContactDao;
import course.contact.dao.CrudContactDao;
import course.post.dao.CrudPostDao;
import course.post.dao.PostDao;
import course.user.dao.CrudUserDao;
import course.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class MysqlDaoFactory {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private static final MysqlDaoFactory instance = new MysqlDaoFactory();
    private javax.sql.DataSource dataSource;
    private UserDao userDao;
    private PostDao postDao;
    private ContactDao contactDao;

    private MysqlDaoFactory() {
        if (instance != null) {
            throw new IllegalStateException("This is a singleton, don't mess with it!");
        }
        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MysqlDaoFactory getInstance(){
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public PostDao getPostDao() {
        return postDao;
    }

    public ContactDao getContactDao() {
        return contactDao;
    }

    private void setup() throws IOException {
        logger.info("setting up mysql dao factory");
        //setup data source
        this.dataSource = initDataSource();
        //setup daos
        logger.debug("instantiating daos");
        this.userDao = new CrudUserDao(this.dataSource);
        this.postDao = new CrudPostDao(this.dataSource);
        this.contactDao = new CrudContactDao(this.dataSource);
        logger.debug("finished setting up mysql dao factory");
    }

    private DataSource initDataSource() throws IOException {
        logger.info("loading database.properties");
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("database.properties"));
        //properties.load(DataSource.class.getClassLoader().getResourceAsStream("database.properties"));
        logger.info("creating datasource");
        com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds
            = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
        String jdbcUrl = properties.getProperty("jdbc.url");
        String jdbcUser = properties.getProperty("jdbc.username");
        String jdbcPassword = properties.getProperty("jdbc.password");
        ds.setUrl(jdbcUrl);
        ds.setUser(jdbcUser);
        ds.setPassword(jdbcPassword);
        return ds;
    }


}


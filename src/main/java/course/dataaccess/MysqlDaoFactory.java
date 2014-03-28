/*
 * Copyright 2012  Research Studios Austria Forschungsges.m.b.H.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package course.dataaccess;


import course.user.dao.CrudUserDao;
import course.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class MysqlDaoFactory
{
  private final Logger logger = LoggerFactory.getLogger(getClass());
  private static final MysqlDaoFactory instance = new MysqlDaoFactory();
  private javax.sql.DataSource dataSource;
  private UserDao userDao;

  private MysqlDaoFactory() {
    if (instance != null) throw new IllegalStateException("This is a singleton, don't mess with it!");
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

  private void setup() throws IOException {
    logger.info("setting up mysql dao factory");
    //setup data source
    this.dataSource = initDataSource();
    //setup daos
    logger.debug("instantiating daos");
    this.userDao = new CrudUserDao(this.dataSource);
    logger.debug("finished setting up mysql dao factory");
  }

    private DataSource initDataSource() throws IOException {
        logger.info("loading database.properties");
        Properties properties = new Properties();
        System.out.println("properties_vorher: ");
        System.out.println(properties);
        try {
            properties.load(DataSource.class.getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            System.out.println("properties: ");
            System.out.println(properties);
            System.out.println("e: ");
            System.out.println(e.toString());
        }
        System.out.println("properties: ");
        System.out.println(properties);
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


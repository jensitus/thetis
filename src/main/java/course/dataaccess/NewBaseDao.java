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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * User: fkleedorfer
 * Date: 06.03.14
 */
public class NewBaseDao {
    private javax.sql.DataSource dataSource;

    public NewBaseDao(final javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** utility methods for subclasses **/

    protected PreparedStatement getPreparedStatement(Connection con, String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    protected void closeConn(Connection con) {
        if (con != null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected javax.sql.DataSource getDataSource() {
        return dataSource;
    }
}

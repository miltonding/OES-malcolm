package com.malcolm.oes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.malcolm.oes.common.JDBCAbstractCallback;
import com.malcolm.oes.common.JdbcTemplate;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class UserDao {
    JdbcTemplate<User> jdbcTemplate = new JdbcTemplate<User>();

    public User getUserByName(final String userName) {
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        String sql = "SELECT * FROM user WHERE user_name = ?";
        User user = jdbcTemplate.queryOne(sql, new JDBCAbstractCallback<User>() {
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, userName);
            };

            public User rsToObject(ResultSet rs) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        return user;
    }
}

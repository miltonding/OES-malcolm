package com.malcolm.oes.dao.jdbc.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.malcolm.oes.dao.UserDao;
import com.malcolm.oes.model.User;
import com.malcolm.oes.util.StringUtil;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserByName(final String userName) {
        if (StringUtil.isEmpty(userName)) {
            return null;
        }
        String sql = "SELECT * FROM user WHERE user_name = ?";
        Object args[] = { userName };
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, args);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }
}

package com.malcolm.oes.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.malcolm.oes.exception.DBException;

public class DBUtil {
    public static Connection getConnection() {
        // 1 Init the driver
        try {
            Class.forName(PropertyUtil.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 2 Get the object of connection
        String jdbcUrl = PropertyUtil.getProperty("jdbc.url");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, PropertyUtil.getProperty("jdbc.username"),
                    PropertyUtil.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException();
        }
        return conn;
    }

    public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
        // 5 Release resource
        try {
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}

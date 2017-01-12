package com.malcolm.oes.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JDBCAbstractCallback<T> implements JDBCCallBack<T> {
    public void setParams(PreparedStatement stmt) throws SQLException {

    }

    public T rsToObject(ResultSet rs) throws SQLException {
        return null;
    }
}

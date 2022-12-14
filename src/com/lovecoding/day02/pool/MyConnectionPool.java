package com.lovecoding.day02.pool;

import com.lovecoding.day01.jdbc.JDBCUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyConnectionPool implements DataSource {

    private List<Connection> connectionList = new ArrayList<>(10);

    public MyConnectionPool() {
        for(int i = 0; i < 10; i ++) {
            connectionList.add(JDBCUtil.getConnection());
        }
    }

    /**
     * 获取连接对象
     * @return
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        if(connectionList.size() == 0) {
            for(int i = 5; i< connectionList.size(); i ++) {
                connectionList.add(JDBCUtil.getConnection());
            }
        }
        return connectionList.remove(0);
    }

    /**
     * 归还连接对象
     * @param conn
     */
    public void releaseConnection(Connection conn) {
        connectionList.add(conn);
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}

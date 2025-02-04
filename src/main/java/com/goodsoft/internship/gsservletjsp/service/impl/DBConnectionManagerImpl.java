package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.service.DBConnectionManager;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManagerImpl implements DBConnectionManager {

    @Getter
    private static final DBConnectionManagerImpl instance = new DBConnectionManagerImpl();
    private Connection connection;

    private DBConnectionManagerImpl() {}

    @Override
    public void createConnection(String username, String password, String dburl, String driver) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(dburl, username, password);
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}

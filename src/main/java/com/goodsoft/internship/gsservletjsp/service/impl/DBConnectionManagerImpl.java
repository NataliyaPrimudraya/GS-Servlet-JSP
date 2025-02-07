package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.service.DBConnectionManager;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DBConnectionManagerImpl implements DBConnectionManager {

    private Connection connection;

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

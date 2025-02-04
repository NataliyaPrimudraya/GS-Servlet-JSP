package com.goodsoft.internship.gsservletjsp.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionManager {

    void createConnection(String username, String password, String dburl, String driver) throws SQLException, ClassNotFoundException;

    Connection getConnection();

}

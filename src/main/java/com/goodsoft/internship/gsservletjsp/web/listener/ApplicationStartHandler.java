package com.goodsoft.internship.gsservletjsp.web.listener;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.DBConnectionManager;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

@WebListener
public class ApplicationStartHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext context = sce.getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        String dburl = context.getInitParameter("dburl");
        String driver = context.getInitParameter("driver");

        try {
            DBConnectionManager connectionManager = ServiceFactory.newInstance().getDBConnectionManagerInstance();
            connectionManager.createConnection(username, password, dburl, driver);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            DBConnectionManager connectionManager = ServiceFactory.newInstance().getDBConnectionManagerInstance();
            connectionManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

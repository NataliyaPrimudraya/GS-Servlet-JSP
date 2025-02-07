package com.goodsoft.internship.gsservletjsp.web.listener;

import com.goodsoft.internship.gsservletjsp.dao.impl.SQLUserDao;
import com.goodsoft.internship.gsservletjsp.service.DBConnectionManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.sql.SQLException;

public class ApplicationStartHandler implements ServletContextListener {

    private DBConnectionManager connectionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        connectionManager = ctx.getBean(DBConnectionManager.class);

        ServletContext context = sce.getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        String dburl = context.getInitParameter("dburl");
        String driver = context.getInitParameter("driver");

        try {
            connectionManager.createConnection(username, password, dburl, driver);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        ctx.getBean(SQLUserDao.class).init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            connectionManager.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.goodsoft.internship.gsservletjsp.servlet;

import com.goodsoft.internship.gsservletjsp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/loginedit.jhtml")
public class LogineditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = (String) req.getSession().getAttribute(USER_INFO_KEY);
        SecurityService securityService = SecurityService.getInstance();
        securityService.updateUser(userLogin, req.getParameter("password"));
        resp.sendRedirect(req.getContextPath() + LOGOUT_PAGE + ".jhtml");
    }
}

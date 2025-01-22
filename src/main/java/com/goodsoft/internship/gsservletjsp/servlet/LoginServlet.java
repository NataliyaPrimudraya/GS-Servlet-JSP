package com.goodsoft.internship.gsservletjsp.servlet;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/login.jhtml")
public class LoginServlet extends HttpServlet {

    private static final String ACTION_PARAM = "action";
    private static final String LOGIN_ACTION_PERFORM = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAM);
        if (LOGIN_ACTION_PERFORM.equals(action)) {
            User userInfo = User.builder()
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .build();
            SecurityService securityService = SecurityService.getInstance();
            if (securityService.isExistingUser(userInfo)) {
                req.getSession().setAttribute(USER_INFO_KEY, userInfo.getLogin());
                resp.sendRedirect(req.getContextPath() + WELCOME_PAGE + ".jhtml");
            } else {
                req.setAttribute("errorMessage", "Неверный логин или пароль");
                req.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(req, resp);
        }
    }
}

package com.goodsoft.internship.gsservletjsp.web.servlet;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;


@WebServlet("/login.jhtml")
public class LoginServlet extends HttpServlet {

    @Autowired
    private UserService userService;
    private static final String ACTION_PARAM = "action";
    private static final String LOGIN_ACTION_PERFORM = "login";

    @Override
    public void init(ServletConfig config) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter(ACTION_PARAM);
        if (LOGIN_ACTION_PERFORM.equals(action)) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            User user = userService.findByLoginAndPassword(login, password);
            if (user != null) {
                req.getSession().setAttribute(USER_INFO_KEY, user);
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

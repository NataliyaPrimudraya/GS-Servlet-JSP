package com.goodsoft.internship.gsservletjsp.web.servlet;

import com.goodsoft.internship.gsservletjsp.dto.UserRequest;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import com.goodsoft.internship.gsservletjsp.service.ValidationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/loginedit.jhtml")
public class LogineditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(req.getParameter("action"), "add")) {
            req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
        } else {
            String stringID = req.getParameter("id");
            if (stringID != null) {
                int userId = Integer.parseInt(stringID);
                ServiceFactory serviceFactory = ServiceFactory.newInstance();
                UserService userService = serviceFactory.getUserServiceInstance();
                if (Objects.equals(req.getParameter("action"), "delete")) {
                    userService.delete(userId);
                    resp.sendRedirect(req.getContextPath() + USERS_LIST_PAGE + ".jhtml");
                } else {
                    req.setAttribute("user", userService.findById(userId));
                    req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
                }
            } else resp.sendRedirect(req.getContextPath() + WELCOME_PAGE + ".jhtml");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = 0;
        if (!Objects.equals(req.getParameter("id"), "")) {
            userId = Integer.parseInt(req.getParameter("id"));
        }

        ServiceFactory serviceFactory = ServiceFactory.newInstance();
        ValidationService validationService = serviceFactory.getValidationServiceInstance();
        List<String> errors = new ArrayList<>(validationService.validateUserRequest(req));

        if (!errors.isEmpty()) {
            UserRequest userRequest = UserRequest.builder()
                    .id(req.getParameter("id"))
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .email(req.getParameter("email"))
                    .surname(req.getParameter("surname"))
                    .name(req.getParameter("name"))
                    .patronymic(req.getParameter("patronymic"))
                    .birthdate(req.getParameter("birthdate"))
                    .role(req.getParameter("role"))
                    .build();
            req.setAttribute("errorMessages", errors);
            req.setAttribute("id", req.getParameter("id"));
            req.setAttribute("user", userRequest);
            req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
        } else {
            User user = User.builder()
                    .id(userId)
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .email(req.getParameter("email"))
                    .surname(req.getParameter("surname"))
                    .name(req.getParameter("name"))
                    .patronymic(req.getParameter("patronymic"))
                    .birthdate(LocalDate.parse(req.getParameter("birthdate")))
                    .role(Role.valueOf(req.getParameter("role")))
                    .build();
            UserService userService = serviceFactory.getUserServiceInstance();
            if (userId == 0) {
                if (userService.save(user) == null) {
                    req.setAttribute("errorMessages", errors);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
                } else resp.sendRedirect(req.getContextPath() + USERS_LIST_PAGE + ".jhtml");
            } else {
                if (userService.findById(userId) != null) {
                    if (userService.update(user) == null) {
                        req.setAttribute("errorMessages", errors);
                        req.setAttribute("id", req.getParameter("id"));
                        req.setAttribute("user", user);
                        req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
                    } else resp.sendRedirect(req.getContextPath() + USERS_LIST_PAGE + ".jhtml");
                } else resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}

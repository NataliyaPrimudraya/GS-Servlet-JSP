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
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/loginedit.jhtml")
public class LogineditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("rolesList", Arrays.asList(Role.values()));
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
        req.setAttribute("rolesList", Arrays.asList(Role.values()));

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
                    .name(req.getParameter("name"))
                    .birthdate(req.getParameter("birthdate"))
                    .age(req.getParameter("age"))
                    .salary(req.getParameter("salary"))
                    .roles(Arrays.asList(req.getParameterValues("roles")))
                    .build();
            req.setAttribute("errorMessages", errors);
            req.setAttribute("id", req.getParameter("id"));
            req.setAttribute("user", userRequest);
            req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
        } else {
            List<Role> roles = Arrays.stream(req.getParameterValues("roles"))
                    .map(Role::valueOf)
                    .collect(Collectors.toList());
            User user = User.builder()
                    .id(userId)
                    .login(req.getParameter("login"))
                    .password(req.getParameter("password"))
                    .name(req.getParameter("name"))
                    .birthdate(Date.valueOf(req.getParameter("birthdate")))
                    .age(Integer.parseInt(req.getParameter("age")))
                    .salary(new BigDecimal(req.getParameter("salary")))
                    .roles(roles)
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

package com.goodsoft.internship.gsservletjsp.servlet;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/loginedit.jhtml")
public class LogineditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(Objects.equals(req.getParameter("action"), "add")){
            req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
        } else {
            String stringID = req.getParameter("id");
            if(stringID!=null){
                int userId = Integer.parseInt(stringID);
                SecurityService securityService = SecurityService.getInstance();
                if(Objects.equals(req.getParameter("action"), "delete")){
                    securityService.deleteUserById(userId);
                    resp.sendRedirect(req.getContextPath() + USERS_LIST_PAGE + ".jhtml");
                } else {
                    req.setAttribute("user", securityService.readUserById(userId));
                    req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
                }
            } else resp.sendRedirect(req.getContextPath() + WELCOME_PAGE + ".jhtml");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SecurityService securityService = SecurityService.getInstance();
        String stringID = req.getParameter("id");
        Map<String, String> params = new HashMap<>();
        params.put("login", req.getParameter("login"));
        params.put("password", req.getParameter("password"));
        params.put("email", req.getParameter("email"));
        params.put("surname", req.getParameter("surname"));
        params.put("name", req.getParameter("name"));
        params.put("patronymic", req.getParameter("patronymic"));
        params.put("birthdate", req.getParameter("birthdate"));
        params.put("role", req.getParameter("role"));

        boolean isValid = true;
        for(String param : params.values()){
            if (param.isEmpty()) {
                isValid = false;
                break;
            }
        }

        User user = User.builder()
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .email(req.getParameter("email"))
                .surname(req.getParameter("surname"))
                .name(req.getParameter("name"))
                .patronymic(req.getParameter("patronymic"))
                .birthdate(LocalDate.parse(req.getParameter("birthdate")))
                .role(Role.valueOf(req.getParameter("role")))
                .build();

        if(isValid){
            if(Objects.equals(stringID, "")){
                securityService.createUser(user);
            } else {
                int userId = Integer.parseInt(stringID);
                if(securityService.readUserById(userId)!=null){
                    user.setId(userId);
                    securityService.updateUser(user);
                }
            }
        } else {
            req.setAttribute("errorMessage", "Неверные данные");
            req.setAttribute("user", user);
            req.getRequestDispatcher(JSP_PATH + LOGIN_EDIT_PAGE + ".jsp").forward(req, resp);
        }
    }
}

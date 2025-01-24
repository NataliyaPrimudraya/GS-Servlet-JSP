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
        System.out.println("id:" + stringID+ " user " + user);
        if(Objects.equals(stringID, "")){
            securityService.createUser(user);
        } else {
            int userId = Integer.parseInt(stringID);
            if(securityService.readUserById(userId)!=null){
                user.setId(userId);
                securityService.updateUser(user);
            }
        }
        resp.sendRedirect(req.getContextPath() + USERS_LIST_PAGE + ".jhtml");
    }
}

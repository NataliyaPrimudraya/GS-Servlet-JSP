package com.goodsoft.internship.gsservletjsp.servlet;

import com.goodsoft.internship.gsservletjsp.service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/userslist.jhtml")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", SecurityService.getInstance().readAllUsers());
        req.getRequestDispatcher(JSP_PATH + USERS_LIST_PAGE + ".jsp").forward(req, resp);
    }
}

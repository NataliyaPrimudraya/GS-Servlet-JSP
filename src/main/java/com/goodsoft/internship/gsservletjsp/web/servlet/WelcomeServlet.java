package com.goodsoft.internship.gsservletjsp.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebServlet("/welcome.jhtml")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PATH + WELCOME_PAGE + ".jsp").forward(req, resp);
    }
}

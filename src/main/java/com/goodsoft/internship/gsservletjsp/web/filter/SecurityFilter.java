package com.goodsoft.internship.gsservletjsp.web.filter;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.goodsoft.internship.gsservletjsp.config.Constants.*;

@WebFilter(urlPatterns = {"/userslist.jhtml", "/loginedit.jhtml"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);
        String welcomeURI = request.getContextPath() + WELCOME_PAGE + ".jhtml";

        User user = (User) session.getAttribute(USER_INFO_KEY);

        if (user.getRole() == Role.ADMIN) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect(welcomeURI);
        }
    }
}

package controller;

import entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SecurityService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

import static config.Constants.*;

@Controller
@RequestMapping("/login.jhtml")
public class LoginController {

    private static final String ACTION_PARAM = "action";
    private static final String LOGIN_ACTION_PERFORM = "login";

    @GetMapping
    public String getLogin() {
        return JSP_PATH + LOGIN_PAGE + ".jsp";
    }

    @PostMapping
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter(ACTION_PARAM);
        if (LOGIN_ACTION_PERFORM.equals(action)) {
            User userInfo = new User(request.getParameter("login"), request.getParameter("password"));
            SecurityService securityService = SecurityService.getInstance();
            if (securityService.isExistingUser(userInfo)) {
                request.getSession().setAttribute(USER_INFO_KEY, userInfo.getLogin());
                response.sendRedirect(request.getServletPath() + "/" + WELCOME_PAGE + ".jhtml");
            } else {
                request.setAttribute("errorMessage", "Неверный логин или пароль");
                request.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher(JSP_PATH + LOGIN_PAGE + ".jsp").forward(request, response);
        }
    }
}

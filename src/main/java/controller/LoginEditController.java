package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SecurityService;

import java.io.IOException;

import static config.Constants.*;

@Controller
@RequestMapping("/loginedit.jhtml")
public class LoginEditController {

    @GetMapping
    public String getLoginEdit() {
        return JSP_PATH + LOGIN_EDIT_PAGE + ".jsp";
    }

    @PostMapping
    public void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userLogin = (String) request.getSession().getAttribute(USER_INFO_KEY);
        SecurityService securityService = SecurityService.getInstance();
        securityService.updateUser(userLogin, request.getParameter("password"));
        response.sendRedirect(APP_PATH + LOGOUT_PAGE + ".jhtml");
    }
}

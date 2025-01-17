package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static config.Constants.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex() {
        return "/index.jsp";
    }

    @GetMapping("/welcome.jhtml")
    public String getWelcome() {
        return JSP_PATH + WELCOME_PAGE + ".jsp";
    }

}

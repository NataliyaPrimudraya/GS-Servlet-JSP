package com.goodsoft.internship.gsservletjsp.web.listener;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.time.LocalDate;

@WebListener
public class ApplicationStartHandler implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServiceFactory serviceFactory = ServiceFactory.newInstance();
        UserService userService = serviceFactory.getUserServiceInstance();

        userService.save(User.builder()
                .login("root")
                .password("root")
                .email("root@gmail.com")
                .surname("Korneev")
                .name("Kornei")
                .patronymic("Korneevich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .role(Role.ADMIN)
                .build());
        userService.save(User.builder()
                .login("user")
                .password("user")
                .email("user@gmail.com")
                .surname("Ivanov")
                .name("Ivan")
                .patronymic("Ivanovich")
                .birthdate(LocalDate.of(1999, 9, 19))
                .role(Role.USER)
                .build());
    }

}

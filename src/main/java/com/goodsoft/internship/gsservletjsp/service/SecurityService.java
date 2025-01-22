package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.ArrayList;

public class SecurityService {

    private ArrayList<User> users = new ArrayList<>();
    private static SecurityService securityService = new SecurityService();

    private SecurityService() {
        users.add(User.builder()
                .login("root")
                .password("root")
                .build());
    }

    public static SecurityService getInstance() {
        return securityService;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void updateUser(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                user.setPassword(password);
            }
        }
    }

    public boolean isExistingUser(User user) {
        return users.contains(user);
    }
}

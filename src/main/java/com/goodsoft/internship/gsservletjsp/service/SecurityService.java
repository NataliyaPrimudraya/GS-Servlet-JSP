package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SecurityService {

    private final ArrayList<User> users = new ArrayList<>();
    private static final SecurityService securityService = new SecurityService();
    private int counter;

    private SecurityService() {
        counter = 1;
        users.add(User.builder()
                .id(counter++)
                .login("root")
                .password("root")
                .email("root@gmail.com")
                .surname("Korneev")
                .name("Kornei")
                .patronymic("Korneevich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .role(Role.ADMIN)
                .build());
        users.add(User.builder()
                .id(counter++)
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

    public static SecurityService getInstance() {
        return securityService;
    }

    public void createUser(User user) {
        user.setId(counter++);
        users.add(user);
    }

    public User readUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User readUserByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public List<User> readAllUsers() {
        return users;
    }

    public void updateUser(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                users.set(users.indexOf(u), user);
            }
        }
    }

    public void deleteUserById(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}

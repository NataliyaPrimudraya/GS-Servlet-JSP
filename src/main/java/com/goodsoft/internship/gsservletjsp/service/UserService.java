package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(int id);

    boolean isLoginTaken(int id, String login);
}

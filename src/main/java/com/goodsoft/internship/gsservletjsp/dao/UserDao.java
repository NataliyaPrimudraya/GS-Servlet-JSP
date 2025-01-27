package com.goodsoft.internship.gsservletjsp.dao;

import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User create(User user);

    User update(User user);

    void delete(int id);

    Optional<User> findById(int id);

    Optional<User> findByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

    List<User> findAll();

}

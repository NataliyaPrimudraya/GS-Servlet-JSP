package com.goodsoft.internship.gsservletjsp.dao.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.data.UserData;
import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.List;
import java.util.Optional;

public class InMemoryUserDao implements UserDao {

    private final UserData data = UserData.getInstance();
    private final List<User> users = data.getUsers();

    @Override
    public User create(User user) {
        user.setId(data.getId());
        users.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User u : users) {
            if (u.getId() == user.getId()) {
                users.set(users.indexOf(u), user);
            }
        }
        return user;
    }

    @Override
    public void delete(int id) {
        users.removeIf(u -> u.getId() == id);
    }

    @Override
    public Optional<User> findById(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return data.getUsers();
    }
}

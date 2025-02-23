package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login).orElse(null);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        if(!isLoginTaken(user.getId(), user.getLogin()))
            return userDao.create(user);
        return null;
    }

    @Override
    public User update(User user) {
        if(userDao.findById(user.getId()).isPresent())
            return userDao.update(user);
        return null;
    }

    @Override
    public void delete(int id) {
        if(userDao.findById(id).isPresent())
            userDao.delete(id);
    }

    @Override
    public boolean isLoginTaken(int id, String login) {
        List<User> usersCopy = new ArrayList<>(userDao.findAll());
        usersCopy.removeIf(u -> u.getId() == id);
        for (User u : usersCopy) {
            if (u.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}

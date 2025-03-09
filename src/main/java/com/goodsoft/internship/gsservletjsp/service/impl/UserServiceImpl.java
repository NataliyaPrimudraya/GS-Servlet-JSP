package com.goodsoft.internship.gsservletjsp.service.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.dto.UserDTO;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ConversionService conversionService;

    @Override
    public UserDTO findById(int id) {
        User user = userDao.findById(id).orElse(null);
        return  conversionService.convert(user, UserDTO.class);
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
    public List<UserDTO> findAll() {
        return userDao.findAll()
                .stream()
                .map((o) -> conversionService.convert(o, UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        if(user!=null && !isLoginTaken(user.getLogin()))
            return conversionService.convert(userDao.create(user), UserDTO.class);
        return null;
    }

    @Override
    public UserDTO update(UserDTO UserDTO) {
        User user = conversionService.convert(UserDTO, User.class);
        if(user!=null && userDao.findById(user.getId()).isPresent() && !isLoginTaken(user.getId(), user.getLogin()))
            return conversionService.convert(userDao.update(user), UserDTO.class);
        return null;
    }

    @Override
    public void delete(int id) {
        if(userDao.findById(id).isPresent())
            userDao.delete(id);
    }

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

    public boolean isLoginTaken(String login) {
        List<User> usersCopy = new ArrayList<>(userDao.findAll());
        for (User u : usersCopy) {
            if (u.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}

package com.goodsoft.internship.gsservletjsp.service;

import com.goodsoft.internship.gsservletjsp.dto.UserDTO;
import com.goodsoft.internship.gsservletjsp.entity.User;

import java.util.List;

public interface UserService {

    UserDTO findById(int id);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    List<UserDTO> findAll();

    UserDTO save(UserDTO user);

    UserDTO update(UserDTO user);

    void delete(int id);
}

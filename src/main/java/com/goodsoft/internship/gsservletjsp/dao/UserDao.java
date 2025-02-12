package com.goodsoft.internship.gsservletjsp.dao;

import com.goodsoft.internship.gsservletjsp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao {

    User create(User user);

    User update(User user);

    void delete(int id);

    Optional<User> findById(int id);

    Optional<User> findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    List<User> findAll();

}

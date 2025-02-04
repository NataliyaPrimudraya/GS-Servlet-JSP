package com.goodsoft.internship.gsservletjsp.dao.impl;

import com.goodsoft.internship.gsservletjsp.dao.UserDao;
import com.goodsoft.internship.gsservletjsp.entity.User;
import com.goodsoft.internship.gsservletjsp.enumeration.Role;
import com.goodsoft.internship.gsservletjsp.service.ServiceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQLUserDao implements UserDao {

    private final Connection conn;

    public SQLUserDao() {
        this.conn = ServiceFactory.newInstance().getDBConnectionManagerInstance().getConnection();
    }

    @Override
    public User create(User user) {
        List<String> roles = user.getRoles().stream().map(Role::toString).collect(Collectors.toList());
        String markers = ",?".repeat(roles.size()).substring(1);

        String userInsertQuery =
                "INSERT INTO updated_auth.users (login, password, name, birthdate, age, salary) VALUES (?, ?, ?, ?, ?, ?)";
        String userRolesInsertQuery =
                "INSERT INTO updated_auth.user_roles (user_id, role_id) " +
                        "SELECT u.id, r.id FROM " +
                        "(SELECT u.id FROM updated_auth.users u WHERE u.login = ?) u, " +
                        "(SELECT r.id FROM updated_auth.roles r WHERE r.name IN (" + markers + ")) r";
        System.out.println(user);
        try (PreparedStatement userStatement = conn.prepareStatement(userInsertQuery);
             PreparedStatement userRolesStatement = conn.prepareStatement(userRolesInsertQuery)) {
            userStatement.setString(1, user.getLogin());
            userStatement.setString(2, user.getPassword());
            userStatement.setString(3, user.getName());
            userStatement.setDate(4, user.getBirthdate());
            userStatement.setInt(5, user.getAge());
            userStatement.setBigDecimal(6, user.getSalary());
            System.out.println(userStatement);
            userStatement.executeUpdate();

            userRolesStatement.setString(1, user.getLogin());
            for (int i = 0; i < roles.size(); i++)
                userRolesStatement.setString(i + 2, roles.get(i));
            userRolesStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findByLogin(user.getLogin());
    }

    @Override
    public User update(User user) {
        List<String> roles = user.getRoles().stream().map(Role::toString).collect(Collectors.toList());
        String markers = ",?".repeat(roles.size()).substring(1);

        String userUpdateQuery =
                "UPDATE updated_auth.users " +
                        "SET login = ?, password = ?, name = ?, birthdate = ?, age = ?, salary = ? " +
                        "WHERE id = ?";
        String userRolesDeleteQuery =
                "DELETE FROM updated_auth.user_roles WHERE user_id = ?";
        String userRolesInsertQuery =
                "INSERT INTO updated_auth.user_roles (user_id, role_id) " +
                        "SELECT u.id, r.id FROM " +
                        "(SELECT u.id FROM updated_auth.users u WHERE u.login = ?) u, " +
                        "(SELECT r.id FROM updated_auth.roles r WHERE r.name IN (" + markers + ")) r";
        try (PreparedStatement userStatement = conn.prepareStatement(userUpdateQuery);
             PreparedStatement roleDeleteStatement = conn.prepareStatement(userRolesDeleteQuery);
             PreparedStatement roleInsertStatement = conn.prepareStatement(userRolesInsertQuery)) {
            userStatement.setString(1, user.getLogin());
            userStatement.setString(2, user.getPassword());
            userStatement.setString(3, user.getName());
            userStatement.setDate(4, user.getBirthdate());
            userStatement.setInt(5, user.getAge());
            userStatement.setBigDecimal(6, user.getSalary());
            userStatement.setInt(7, user.getId());
            userStatement.executeUpdate();

            roleDeleteStatement.setInt(1, user.getId());
            roleDeleteStatement.executeUpdate();

            roleInsertStatement.setString(1, user.getLogin());
            for (int i = 0; i < roles.size(); i++)
                roleInsertStatement.setString(i + 2, roles.get(i));
            roleInsertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findByLogin(user.getLogin());
    }

    @Override
    public void delete(int id) {
        String userQuery = "DELETE FROM updated_auth.users WHERE id = ?";
        String userRolesQuery = "DELETE FROM updated_auth.user_roles WHERE user_id = ?";
        try (PreparedStatement userStatement = conn.prepareStatement(userQuery)) {
            userStatement.setInt(1, id);
            userStatement.executeUpdate();

            PreparedStatement userRolesStatement = conn.prepareStatement(userRolesQuery);
            userRolesStatement.setInt(1, id);
            userRolesStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(int id) {
        User user = null;
        String query =
                "SELECT * " +
                        "FROM updated_auth.users u " +
                        "WHERE u.id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthdate(rs.getDate("birthdate"))
                        .age(rs.getInt("age"))
                        .salary(rs.getBigDecimal("salary"))
                        .roles(findAllRolesByUserId(rs.getInt("id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    public User findByLogin(String login) {
        String query =
                "SELECT * " +
                        "FROM updated_auth.users u " +
                        "WHERE u.login = ?";
        User user = null;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthdate(rs.getDate("birthdate"))
                        .age(rs.getInt("age"))
                        .salary(rs.getBigDecimal("salary"))
                        .roles(findAllRolesByUserId(rs.getInt("id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        User user = null;
        String query =
                "SELECT * " +
                        "FROM updated_auth.users u " +
                        "WHERE u.login = ? and u.password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthdate(rs.getDate("birthdate"))
                        .age(rs.getInt("age"))
                        .salary(rs.getBigDecimal("salary"))
                        .roles(findAllRolesByUserId(rs.getInt("id")))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM updated_auth.users ORDER BY id";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("name"))
                        .birthdate(rs.getDate("birthdate"))
                        .age(rs.getInt("age"))
                        .salary(rs.getBigDecimal("salary"))
                        .roles(findAllRolesByUserId(rs.getInt("id")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Role> findAllRolesByUserId(int id) {
        List<Role> roles = new ArrayList<>();
        String query =
                "SELECT r.name " +
                        "FROM updated_auth.user_roles ur " +
                        "JOIN updated_auth.users u ON u.id = ur.user_id " +
                        "JOIN updated_auth.roles r ON ur.role_id = r.id " +
                        "WHERE u.id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                roles.add(Role.valueOf(rs.getString("name").toUpperCase()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

}

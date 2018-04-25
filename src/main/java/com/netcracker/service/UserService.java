package com.netcracker.service;

import com.netcracker.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface UserService extends ServiceInterface<User> {
    List<User> getAll() throws SQLException;

    User getByUsername(String username) throws SQLException;

    void register(User e) throws SQLException;

    void delete(User e) throws SQLException;

    void update(User e) throws SQLException;
}

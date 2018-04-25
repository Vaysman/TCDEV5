package com.netcracker.dao;

import com.netcracker.model.User;

import java.sql.SQLException;

public interface UserDAO extends DAO<User> {
    User getByUsername(String username) throws SQLException;
}

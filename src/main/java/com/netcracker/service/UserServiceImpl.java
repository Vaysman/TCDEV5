package com.netcracker.service;

import com.netcracker.dao.UserDAOImpl;
import com.netcracker.dao.UserDAO;
import com.netcracker.model.Role;
import com.netcracker.model.User;
import com.netcracker.model.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.SQLException;
import java.util.List;


public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    private BCryptPasswordEncoder encoder;

    public UserServiceImpl(){
        userDAO = new UserDAOImpl();
        encoder = new BCryptPasswordEncoder();
    }

    public List<User> getAll() {
        try {
            return userDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByUsername(String username) {
        try{
            return userDAO.getByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(User e) throws SQLException{
        e.setPassword(encoder.encode(e.getPassword()));
        e.setRole(Role.ROLE_USER);
        e.setState(UserState.ACTIVE);
        userDAO.create(e);
    }

    public void delete(User e) throws SQLException {
        userDAO.delete(e.getId());
    }

    public void update(User e) throws SQLException {
        userDAO.update(e);
    }
}

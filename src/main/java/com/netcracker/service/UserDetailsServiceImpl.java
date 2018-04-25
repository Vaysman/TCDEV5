package com.netcracker.service;

import com.netcracker.dao.UserDAOImpl;
import com.netcracker.dao.UserDAO;
import com.netcracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

class UserDetailsServiceImpl implements UserDetailsService {
    private UserDAO userDao;

    public UserDetailsServiceImpl(){
        userDao = new UserDAOImpl();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.getByUsername(username);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    grantedAuthorities
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
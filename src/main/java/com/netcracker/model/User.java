package com.netcracker.model;

import java.util.UUID;


public class User {
    private UUID id;
    private String username;
    private String password;
    private Role role;
    private String fullname;
    private String email;
    private UserState state;
    private UUID cashBoxId;

    public void setCashBoxId(UUID cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public UUID getCashBoxId() {
        return cashBoxId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.Role;
import com.netcracker.model.User;
import com.netcracker.model.UserState;
import org.springframework.lang.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDAOImpl extends AbstractEAVDAO<User> implements UserDAO {

  private User setUserPropertiesFromResultSet(@NonNull ResultSet rs, Connection c) throws SQLException{
    Map<String, String> userAttributes;
    User user = new User();
    user.setUsername(rs.getString(1));
    user.setId(UUID.fromString(rs.getString(2)));
    user.setPassword(rs.getString(3));
    user.setRole(Role.valueOf(rs.getString(4)));
    userAttributes = getAttributesAndValuesOfObjectById(user.getId(),c);
    user.setFullname(userAttributes.get("fullname"));
    user.setEmail(userAttributes.get("email"));
    user.setState(UserState.valueOf(userAttributes.get("user_state")));
    return user;
  }

  public List<User> getAll() throws SQLException {
    List<User> users = new ArrayList<>();
    Connection connection = ConnectionManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_ALL_USERS);
    ResultSet resultSet = preparedStatement.executeQuery();

    while (resultSet.next()) {
      users.add(setUserPropertiesFromResultSet(resultSet,connection));
    }
    connection.close();
    return users;
  }


  public User getByUsername(String username) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_BY_USERNAME);
    preparedStatement.setString(1, username);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    User user = setUserPropertiesFromResultSet(resultSet,connection);
    connection.close();
    return user;
  }

  public User getById(UUID id) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_USER_BY_ID);
    preparedStatement.setString(1, id.toString());
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    User user = setUserPropertiesFromResultSet(resultSet,connection);
    connection.close();
    return user;
  }

  public void delete(UUID id) throws SQLException {
    String sql = "DELETE from objects WHERE id=?::uuid;";
    Connection connection = ConnectionManager.getInstance().getConnection();
    PreparedStatement ps = connection.prepareStatement(sql);
    ps.setString(1,id.toString());
    ps.executeUpdate();
    connection.close();
  }

  public void create(User user) throws SQLException{
    UUID uuid = UUID.randomUUID();
    Connection connection = ConnectionManager.getInstance().getConnection();
    connection.setAutoCommit(false);
    PreparedStatement ps = connection.prepareStatement(SQLQueries.CREATE_USER);
    ps.setString(1,uuid.toString());
    ps.setString(2,user.getUsername());
    ps.setString(3,user.getRole().toString());
    ps.setString(4,user.getPassword());
    ps.executeUpdate();
    setAttributeValue("email",user.getEmail(),UUID.randomUUID(),uuid,connection);
    setAttributeValue("fullname",user.getFullname(),UUID.randomUUID(),uuid,connection);
    setAttributeValue("user_state",user.getState().toString(),UUID.randomUUID(),uuid,connection);
    connection.commit();
    connection.close();
  }

  public void update(User user) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    connection.setAutoCommit(false);
    PreparedStatement ps = connection.prepareStatement(SQLQueries.UPDATE_USER);
    ps.setString(1,user.getUsername());
    ps.setString(2,user.getPassword());
    ps.setString(3,user.getRole().toString());
    ps.setString(4,user.getId().toString());
    ps.executeUpdate();
    updateAttributeValue("email",user.getEmail(),user.getId(),connection);
    updateAttributeValue("fullname",user.getFullname(),user.getId(),connection);
    updateAttributeValue("user_state",user.getState().toString(),user.getId(),connection);
    connection.commit();
    connection.close();
  }
}

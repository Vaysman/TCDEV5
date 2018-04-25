package com.netcracker.dao;

public class SQLQueries {
  static final public String GET_ATTRIBUTE_VALUE_BY_NAME =
          "SELECT attr_value.value " +
                  "FROM attributes " +
                  "INNER JOIN attr_value " +
                  "ON attr_value.attr_id = attributes.id AND attributes.attr_name = ? " +
                  "WHERE obj_id = ?::uuid";

  static final public String SET_ATTRIBUTE_VALUE_BY_NAME =
          "INSERT INTO attr_value " +
                  "VALUES (?::uuid, ?::uuid, (SELECT attributes.id FROM attributes WHERE attributes.attr_name = ?), ?)";

  static final public String GET_ALL_ATTRIBUTES_BY_OBJECT_ID =
          "SELECT a.attr_name, av.value " +
                  "FROM attr_value AS av " +
                  "INNER JOIN attributes AS a ON av.attr_id = a.id " +
                  "WHERE obj_id=?::uuid";

  static final public String GET_ALL_COMMENTS =
          "SELECT objects.id " +
                  "FROM objects " +
                  "INNER JOIN obj_type ON obj_type.id = objects.type_id AND obj_type.type_name = 'comment'";

  static final public String GET_COMMENT_BY_MATCH_ID =
          "SELECT obj_id " +
                  "FROM attr_value " +
                  "WHERE attr_name = 'match_id' AND value = ?";

  static final public String DELETE_COMMENT_BY_ID =
          "DELETE FROM objects WHERE objects.id = ?::uuid";

  static final public String CREATE_COMMENT =
          "INSERT INTO objects " +
                  "VALUES(?::uuid, (SELECT obj_type.id FROM obj_type WHERE type_name = 'comment'))";

  static final public String GET_ALL_MATCHES =
          "SELECT objects.id " +
                  "FROM objects " +
                  "INNER JOIN obj_type " +
                  "ON obj_type.id = objects.type_id AND obj_type.type_name = 'match'";

  static final public String DELETE_MATCH_BY_ID =
          "DELETE FROM objects WHERE objects.id = ?::uuid";

  static final public String CREATE_MATCH =
          "INSERT INTO objects " +
                  "VALUES(?::uuid, (SELECT obj_type.id FROM obj_type WHERE type_name = 'match'))";

  static final public String GET_ALL_USERS =
          "SELECT u.username, u.user_id, u.password, ur.role_name  " +
                  "FROM users AS u " +
                  "INNER JOIN user_roles AS ur ON u.role_id=ur.role_id;";

  static final public String GET_USER_BY_USERNAME =
          "SELECT u.username, u.user_id, u.password, ur.role_name " +
                  "FROM users AS u " +
                  "INNER JOIN user_roles AS ur ON u.role_id=ur.role_id " +
                  "WHERE u.username=?";

  static final public String GET_USER_BY_ID =
          "SELECT u.username, u.user_id, u.password, ur.role_name " +
                  "FROM users AS u " +
                  "INNER JOIN user_roles AS ur ON u.role_id=ur.role_id " +
                  "WHERE u.user_id=?::uuid";

  static final public String CREATE_USER =
          "WITH ins1 AS (" +
                  "  INSERT INTO objects(id,type_id)" +
                  "  VALUES (?::uuid,(SELECT id FROM obj_type WHERE obj_type.type_name='user'))" +
                  "  RETURNING id AS ret_obj_id" +
                  ")" +
                  "  INSERT INTO users(username, role_id, user_id, password)" +
                  "  VALUES (?," +
                  "          (SELECT role_id FROM user_roles WHERE role_name=?)," +
                  "          (SELECT ret_obj_id FROM ins1)," +
                  "          ?);";

  static final public String UPDATE_USER =
          "UPDATE users SET" +
                  "    username=?," +
                  "    password=?," +
                  "    role_id=(SELECT role_id FROM user_roles WHERE role_name=?)" +
                  "  WHERE user_id=?::uuid";

  static final public String UPDATE_ATTRIBUTE_VALUE_BY_NAME =
          "UPDATE attr_value " +
                  "SET value = ? " +
                  "WHERE attr_value.attr_id = "
                  + "(SELECT attributes.id FROM attributes WHERE attributes.attr_name = ?)"
                  + " AND attr_value.obj_id = ?::uuid";

  static final public String GET_ALL_BETS =
          "SELECT objects.id " +
                  "FROM objects " +
                  "INNER JOIN obj_type ON obj_type.id = objects.type_id AND obj_type.type_name = 'bet'";


  static final public String DELETE_BY_ID =
          "DELETE FROM objects WHERE objects.id = ?::uuid";

  static final public String CREATE_BET =
          "INSERT INTO objects " +
                  "VALUES(?::uuid, (SELECT obj_type.id FROM obj_type WHERE type_name = 'bet'))";

  static final public String GET_ALL_TEAMS =
          "SELECT objects.id " +
                  "FROM objects " +
                  "INNER JOIN obj_type ON obj_type.id = objects.type_id AND obj_type.type_name = 'team'";

  static final public String CREATE_TEAM =
          "INSERT INTO objects " +
                  "VALUES(?::uuid, (SELECT obj_type.id FROM obj_type WHERE type_name = 'team'))";

  static final public String GET_ALL_CASHBOXES =
          "SELECT objects.id " +
                  "FROM objects " +
                  "INNER JOIN obj_type ON obj_type.id = objects.type_id AND obj_type.type_name = 'cash_box'";

  static final public String CREATE_CASHBOX =
          "INSERT INTO objects " +
                  "VALUES(?::uuid, (SELECT obj_type.id FROM obj_type WHERE type_name = 'cash_box'))";
}

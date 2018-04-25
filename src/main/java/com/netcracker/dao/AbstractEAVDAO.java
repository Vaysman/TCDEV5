package com.netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Representation of Abstract DAO for EAV database.
 * @param <Entity>  This describes type of Entity.
 */
public abstract class AbstractEAVDAO <Entity> implements DAO<Entity> {

  protected Map <String, PreparedStatement> preparedStatementMap;

  /**
   * Constructor of AbstractEAVDAO.
   */
  public AbstractEAVDAO() {
    preparedStatementMap = new HashMap<String, PreparedStatement>();

  }

  /**
   * Returns prepared statement from map of existing prepared statements.
   * If specified prepared statement was already created.It won't be necessary to create one more same.
   * @param sql is sql query for prepared statement.
   * @param connection is instance of java.sql.Connection.
   * @return instance of PreparedStatement.
   * @throws SQLException
   */
  protected PreparedStatement getPreparedStatement(String sql, Connection connection) throws SQLException {
    if (!preparedStatementMap.containsKey(sql)) {
      preparedStatementMap.put(sql, connection.prepareStatement(sql));
    }
    return preparedStatementMap.get(sql);
  }

  /**
   * Returns value of attribute with specified name.
   * @param attributeName is name of attribute to get.
   * @param objectId is id of instance to which the value is attached.
   * @param connection is instance of java.sql.Connection.
   * @return String with value of attribute.
   * @throws SQLException
   */
  protected String getAttributeValue(String attributeName, UUID objectId, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.GET_ATTRIBUTE_VALUE_BY_NAME, connection);
    preparedStatement.setString(1, attributeName);
    preparedStatement.setString(2, objectId.toString());
    preparedStatement.execute();
    ResultSet result = preparedStatement.getResultSet();
    result.next();
    return result.getString("value");
  }

  /**
   * Sets value of attribute with specified name.
   * @param attributeName is name of attribute to set.
   * @param value is value to set.
   * @param valueId is id of value to set.
   * @param objectId is id of instance to which the value is attached.
   * @param connection is instance of java.sql.Connection.
   * @throws SQLException
   */
  protected void setAttributeValue(String attributeName, String value, UUID valueId, UUID objectId, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.SET_ATTRIBUTE_VALUE_BY_NAME, connection);
    preparedStatement.setString(1, valueId.toString());
    preparedStatement.setString(2, objectId.toString());
    preparedStatement.setString(3, attributeName);
    preparedStatement.setString(4, value);
    preparedStatement.executeUpdate();
  }

  /**
   * Uodates value of attribute with specified name.
   * @param attributeName is name of attribute to update.
   * @param value is value to set.
   * @param objectId is id of instance to which the value is attached.
   * @param connection is instance of java.sql.Connection.
   * @throws SQLException
   */
  protected void updateAttributeValue(String attributeName, String value, UUID objectId, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.UPDATE_ATTRIBUTE_VALUE_BY_NAME, connection);
    preparedStatement.setString(1, value);
    preparedStatement.setString(2, attributeName);
    preparedStatement.setString(3, objectId.toString());
    preparedStatement.executeUpdate();
  }


  protected Map<String, String> getAttributesAndValuesOfObjectById(UUID id, Connection connection) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_ALL_ATTRIBUTES_BY_OBJECT_ID);
    preparedStatement.setString(1, id.toString());
    ResultSet rs = preparedStatement.executeQuery();
    Map<String, String> attributes = new HashMap<String, String>();
    while (rs.next()) {
      attributes.put(rs.getString(1), rs.getString(2));
    }
    return attributes;
  }
}



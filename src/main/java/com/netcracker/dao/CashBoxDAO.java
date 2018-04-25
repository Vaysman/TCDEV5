package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.CashBox;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CashBoxDAO extends AbstractEAVDAO<CashBox> {
  @Override
  public List<CashBox> getAll() throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<CashBox> cashBoxList = new LinkedList<CashBox>();
    try {
      Statement statement = connection.createStatement();
      statement.executeQuery(SQLQueries.GET_ALL_CASHBOXES);
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        UUID cashBoxId = UUID.fromString(result.getString("id"));
        Map<String, String> cashBoxAttributes = getAttributesAndValuesOfObjectById(cashBoxId, connection);

        CashBox cashBox = new CashBox();
        cashBox.setId(cashBoxId);
        cashBox.setUserId(UUID.fromString(cashBoxAttributes.get("cash_box_user_id")));
        cashBox.setSum(new BigDecimal(cashBoxAttributes.get("cash_box_sum")));
        cashBoxList.add(cashBox);
      }
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return cashBoxList;
  }

  @Override
  public CashBox getById(UUID id) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    CashBox cashBox = new CashBox();
    try {
      cashBox.setId(id);
      cashBox.setUserId(UUID.fromString(getAttributeValue("cash_box_user_id", id, connection)));
      cashBox.setSum(new BigDecimal(getAttributeValue("cash_box_sum", id, connection)));
      connection.close();
    } catch (SQLException exception) {

    }
    return cashBox;
  }

  @Override
  public void delete(UUID id) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.DELETE_BY_ID, connection);
      preparedStatement.setString(1, id.toString());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException exception) {

    }
  }

  @Override
  public void create(CashBox cashBox) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.CREATE_CASHBOX, connection);
      preparedStatement.setString(1, cashBox.getId().toString());
      preparedStatement.executeUpdate();
      this.setAttributeValue("cash_box_user_id", cashBox.getUserId().toString(), UUID.randomUUID(), cashBox.getId(), connection);
      this.setAttributeValue("cash_box_sum", cashBox.getSum().toString(), UUID.randomUUID(), cashBox.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }

  @Override
  public void update(CashBox cashBox) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      this.updateAttributeValue("cash_box_user_id", cashBox.getUserId().toString(), cashBox.getId(), connection);
      this.updateAttributeValue("cash_box_sum", cashBox.getSum().toString(),  cashBox.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }
}

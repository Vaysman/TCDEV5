package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.Bet;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BetDAO extends AbstractEAVDAO<Bet> {

  @Override
  public List<Bet> getAll() throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<Bet> betList = new LinkedList<Bet>();
    try {
      Statement statement = connection.createStatement();
      statement.executeQuery(SQLQueries.GET_ALL_BETS);
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        UUID betId = UUID.fromString(result.getString("id"));
        Map<String, String> betAttributes = getAttributesAndValuesOfObjectById(betId, connection);

        Bet bet = new Bet();
        bet.setId(betId);
        bet.setUserId(UUID.fromString(betAttributes.get("bet_user_id")));
        bet.setMatchId(UUID.fromString(betAttributes.get("bet_match_id")));
        bet.setBetSum(new BigDecimal(betAttributes.get("bet_sum")));
        bet.setCoefficient(new BigDecimal(betAttributes.get("bet_coefficient")));
        bet.setTeamId(UUID.fromString(betAttributes.get("bet_team_id")));
        betList.add(bet);
      }
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return betList;
  }

  @Override
  public Bet getById(UUID id) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    Bet bet = new Bet();
    try {
      bet.setId(id);
      bet.setMatchId(UUID.fromString(getAttributeValue("bet_match_id", id, connection)));
      bet.setUserId(UUID.fromString(getAttributeValue("bet_user_id", id, connection)));
      bet.setBetSum(new BigDecimal(getAttributeValue("bet_sum", id, connection)));
      bet.setCoefficient(new BigDecimal(getAttributeValue("bet_coefficient", id, connection)));
      bet.setTeamId(UUID.fromString(getAttributeValue("bet_team_id", id, connection)));
      connection.close();
    } catch (SQLException exception) {

    }
    return bet;
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
  public void create(Bet bet) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.CREATE_BET, connection);
      preparedStatement.setString(1, bet.getId().toString());
      preparedStatement.executeUpdate();
      this.setAttributeValue("bet_match_id", bet.getMatchId().toString(), UUID.randomUUID(), bet.getId(), connection);
      this.setAttributeValue("bet_user_id", bet.getUserId().toString(), UUID.randomUUID(), bet.getId(), connection);
      this.setAttributeValue("bet_sum", bet.getBetSum().toString(), UUID.randomUUID(), bet.getId(), connection);
      this.setAttributeValue("bet_coefficient", bet.getCoefficient().toString(), UUID.randomUUID(), bet.getId(), connection);
      this.setAttributeValue("bet_team_id", bet.getTeamId().toString(), UUID.randomUUID(), bet.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }

  @Override
  public void update(Bet bet) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      this.updateAttributeValue("bet_match_id", bet.getMatchId().toString(), bet.getId(), connection);
      this.updateAttributeValue("bet_user_id", bet.getUserId().toString(),  bet.getId(), connection);
      this.updateAttributeValue("bet_sum", bet.getBetSum().toString(), bet.getId(), connection);
      this.updateAttributeValue("bet_coefficient", bet.getCoefficient().toString(), bet.getId(), connection);
      this.updateAttributeValue("bet_team_id", bet.getTeamId().toString(), bet.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }
}

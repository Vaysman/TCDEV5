package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.Team;

import java.math.BigDecimal;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TeamDAO extends AbstractEAVDAO<Team> {

  @Override
  public List<Team> getAll() throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<Team> teamList = new LinkedList<Team>();
    try {
      Statement statement = connection.createStatement();
      statement.executeQuery(SQLQueries.GET_ALL_TEAMS);
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        UUID teamId = UUID.fromString(result.getString("id"));
        Map<String, String> teamAttributes = getAttributesAndValuesOfObjectById(teamId, connection);

        Team team = new Team();
        team.setId(teamId);
        team.setTeamName(teamAttributes.get("team_name"));
        team.setRating(new BigDecimal(teamAttributes.get("team_rating")));

        teamList.add(team);
      }
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return teamList;
  }

  @Override
  public Team getById(UUID id)  {
    Connection connection = ConnectionManager.getInstance().getConnection();
    Team team = new Team();
    try {
      team.setId(id);
      team.setTeamName(getAttributeValue("team_name", id, connection));
      team.setRating(new BigDecimal(getAttributeValue("team_rating", id, connection)));
      connection.close();
    } catch (SQLException exception) {

    }
    return team;
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
  public void create(Team team) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.CREATE_TEAM, connection);
      preparedStatement.setString(1, team.getId().toString());
      preparedStatement.executeUpdate();
      this.setAttributeValue("team_name", team.getTeamName(), UUID.randomUUID(), team.getId(), connection);
      this.setAttributeValue("team_rating", team.getRating().toString(), UUID.randomUUID(), team.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }

  @Override
  public void update(Team team) throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      this.updateAttributeValue("team_name", team.getTeamName(), team.getId(), connection);
      this.updateAttributeValue("team_rating", team.getRating().toString(),  team.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }
}

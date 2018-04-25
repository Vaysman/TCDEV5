package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.Competition;
import com.netcracker.model.Match;
import com.netcracker.model.MatchState;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Representation of DAO for Match.
 */
public class MatchDAO extends AbstractEAVDAO<Match> {

  public List<Match> getAll() throws SQLException {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<Match> matchList = new LinkedList<Match>();
    try {
      Statement statement = connection.createStatement();
      statement.executeQuery(SQLQueries.GET_ALL_MATCHES);
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        UUID matchId = UUID.fromString(result.getString("id"));
        Map<String, String> matchAttributes = getAttributesAndValuesOfObjectById(matchId, connection);

        Match match = new Match();
        match.setId(matchId);
        match.setDate(new Date(matchAttributes.get("match_date")));
        match.setMatchState(MatchState.valueOf(matchAttributes.get("match_state")));
        match.setCompetition(Competition.valueOf(matchAttributes.get("match_competition")));
        match.setCompetitorsAndScores(UUID.fromString(matchAttributes.get("match_team_A")), new Integer(matchAttributes.get("match_score_A")));
        match.setCompetitorsAndScores(UUID.fromString(matchAttributes.get("match_team_B")), new Integer(matchAttributes.get("match_score_B")));
        matchList.add(match);
      }
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return matchList;
  }

  public Match getById(UUID id) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    Match match = new Match();
    try {
      match.setId(id);
      match.setMatchState(MatchState.valueOf(getAttributeValue("match_state", id, connection)));
      match.setDate(new Date(getAttributeValue("match_date", id, connection)));
      match.setCompetition(Competition.valueOf(getAttributeValue("match_competition", id, connection)));
      match.setCompetitorsAndScores(UUID.fromString(getAttributeValue("match_team_A", match.getId(), connection)),
              new Integer(getAttributeValue("match_score_A", match.getId(), connection)));
      match.setCompetitorsAndScores(UUID.fromString(getAttributeValue("match_team_B", match.getId(), connection)),
              new Integer(getAttributeValue("match_score_B", match.getId(), connection)));
      connection.close();
    } catch (SQLException exception) {

    }
    return match;
  }

  public void delete(UUID id) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.DELETE_MATCH_BY_ID, connection);
      preparedStatement.setString(1, id.toString());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException exception) {

    }
  }

  public void create(Match match) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.CREATE_MATCH, connection);
      preparedStatement.setString(1, match.getId().toString());
      preparedStatement.executeUpdate();
      this.setAttributeValue("match_state", match.getMatchState().toString(), UUID.randomUUID(), match.getId(), connection);
      this.setAttributeValue("match_date", match.getDate().toString(), UUID.randomUUID(), match.getId(), connection);
      this.setAttributeValue("match_competition", match.getCompetition().toString(), UUID.randomUUID(), match.getId(), connection);
      /*
      String[] competitorsNames = new String[2];
      match.getCompetitorsNames().toArray(competitorsNames);
      */
      Iterator<UUID> competitorsNames = match.getCompetitorsIDs().iterator();
      while(competitorsNames.hasNext()) {
        this.setAttributeValue("match_team_A", competitorsNames.next().toString(), UUID.randomUUID(), match.getId(), connection);
        this.setAttributeValue("match_team_B", competitorsNames.next().toString(), UUID.randomUUID(), match.getId(), connection);
      }
      /*
      Integer[] competitorsScores = new Integer[2];
      match.getCompetitorsScores().toArray(competitorsScores);
      */
      Iterator<Integer> competitorsScores = match.getCompetitorsScores().iterator();
      while(competitorsNames.hasNext()) {
        this.setAttributeValue("match_score_A", competitorsScores.next().toString(), UUID.randomUUID(), match.getId(), connection);
        this.setAttributeValue("match_score_B", competitorsScores.next().toString(), UUID.randomUUID(), match.getId(), connection);
      }
      connection.close();
    } catch (SQLException exception) {

    }
  }

  public void update(Match match) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      this.updateAttributeValue("match_state", match.getMatchState().toString(), match.getId(), connection);
      this.updateAttributeValue("match_date", match.getDate().toString(), match.getId(), connection);
      this.updateAttributeValue("match_competition", match.getCompetition().toString() , match.getId(), connection);
      /*
      String[] competitorsNames = new String[2];
      match.getCompetitorsNames().toArray(competitorsNames);
      */

      Iterator<UUID> competitorsNames = match.getCompetitorsIDs().iterator();
      while(competitorsNames.hasNext()) {
        this.updateAttributeValue("match_team_A", competitorsNames.next().toString(),UUID.randomUUID(), connection);
        this.updateAttributeValue("match_team_B", competitorsNames.next().toString(),UUID.randomUUID(), connection);
      }

      /*
      Integer[] competitorsScores = new Integer[2];
      match.getCompetitorsScores().toArray(competitorsScores);
      */
      Iterator<Integer> competitorsScores = match.getCompetitorsScores().iterator();
      while(competitorsScores.hasNext()) {
        this.updateAttributeValue("match_score_A", competitorsScores.next().toString(), UUID.randomUUID(), connection);
        this.updateAttributeValue("match_score_B", competitorsScores.next().toString(), UUID.randomUUID(), connection);
      }
      connection.close();
    } catch (SQLException exception) {

    }
  }
}

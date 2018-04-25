package com.netcracker.service;

import com.netcracker.dao.MatchDAO;
import com.netcracker.model.*;

import java.sql.SQLException;
import java.util.*;

/**
 * Representation of Match Service.
 */
public class MatchService implements ServiceInterface<Match> {

  private MatchDAO matchDAO;
  private TeamService teamService;

  /**
   * Constructor.
   * Makes an instance of MatchService.
   */
  public MatchService() {
    matchDAO = new MatchDAO();
    teamService = new TeamService();
  }

  /**
   * Returns list of all matches from database.
   * @return list of matches.
   */
  public List<Match> getAll() {
    try {
      return matchDAO.getAll();
    } catch (SQLException exception) {

    }
    return null;
  }

  /**
   * Returns match with specified id.
   * @param id is id of Match.
   * @return an instance of Match.
   */
  public Match getById(UUID id) {
    return matchDAO.getById(id);
  }

  /**
   * Saves match in database.
   * @param match is instance of match to save.
   */
  public void save(Match match) {
    matchDAO.create(match);
  }

  /**
   * Deletes match from database.
   * @param match is instance of match to delete.
   */
  public void delete(Match match) {
    matchDAO.delete(match.getId());
  }

  /**
   * Updates match in database.
   * @param match is instance of Match.
   */
  public void update(Match match) {
    matchDAO.update(match);
  }

  /**
   * Closes match.
   * @param match to close.
   * @throws SQLException
   */
  public void close(Match match) throws SQLException {
    match.setMatchState(MatchState.CLOSED);
    teamService.updateCompetitorRatings(match);
    matchDAO.update(match);
  }
}

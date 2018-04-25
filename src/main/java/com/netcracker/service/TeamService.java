package com.netcracker.service;

import com.netcracker.dao.TeamDAO;
import com.netcracker.model.Match;
import com.netcracker.model.MatchState;
import com.netcracker.model.Team;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Representation of Team Service.
 */
public class TeamService {

  private TeamDAO teamDAO;

  /**
   * Constructor.
   * Makes an instance of TeamService.
   */
  public TeamService() {
    teamDAO = new TeamDAO();
  }


  /**
   * Returns list of all teams from database.
   * @return list of teams.
   */
  public List <Team> getAll() throws SQLException {
    return teamDAO.getAll();
  }

  /**
   * Returns team with specified id.
   * @param id is id of Team.
   * @return an instance of Team.
   */
  public Team getById(UUID id) {
    return teamDAO.getById(id);
  }

  /**
   * Updates team in database.
   * @param team is instance of Team.
   */
  public void update(Team team) throws SQLException {
    teamDAO.update(team);
  }

  /**
   * Saves team in database.
   * @param team is instance of team to save.
   */
  public void create(Team team) throws SQLException {
    teamDAO.create(team);
  }

  /**
   * Deletes team from database.
   * @param team is instance of team to delete.
   */
  public void delete(Team team) throws SQLException {
    teamDAO.delete(team.getId());
  }

  /**
   * Updates team ratings that participated in the game.
   * @param match is match where teams participated.
   * @throws SQLException
   */
  public void updateCompetitorRatings(Match match) throws SQLException {
    if (!match.getMatchState().equals(MatchState.CLOSED)) {
      return;
    }
    List<Team> teams =  this.getSortedByScoreListOfCompetitors(match);
    for (int i = 0; i < teams.size(); i++) {
      BigDecimal rating = this.calculatePoints(teams.get(i + 1), teams.get(i));
      teams.get(i).decreaseRating(rating);
      teams.get(i + 1).increaseRating(rating);
    }
    for (Team team : teams) {
      teamDAO.update(team);
    }
  }

  /**
   * Returns list of teams sorted by score.
   * @param match is match in wich they played.
   * @return list of teams.
   */
  public List<Team> getSortedByScoreListOfCompetitors(Match match) {
    return match.getCompetitorsAndScores().entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).
            map(entrySet -> teamDAO.getById(entrySet.getKey())).collect(Collectors.toList());
  }

  private BigDecimal calculatePoints(Team winner, Team loser) {
    if (winner.getRating().compareTo(loser.getRating()) == 0) {
      return winner.getRating().scaleByPowerOfTen(-2).multiply(new BigDecimal(17));
    }
    if (winner.getRating().compareTo(loser.getRating()) == 1) {
      BigDecimal onePercentOfBiggerRating = winner.getRating().scaleByPowerOfTen(-2);
      BigDecimal difference = winner.getRating().subtract(loser.getRating());
      return loser.getRating().multiply(difference.divide(onePercentOfBiggerRating).scaleByPowerOfTen(-2));
    } else {
      BigDecimal onePercentOfBiggerRating = loser.getRating().scaleByPowerOfTen(-2);
      BigDecimal difference = loser.getRating().subtract(winner.getRating());
      return winner.getRating().multiply(difference.divide(onePercentOfBiggerRating).scaleByPowerOfTen(-2));
    }
    /*
    if (winner.getRating().compareTo(loser.getRating()) == 0) {
      return winner.getRating().scaleByPowerOfTen(-1);
    }
    BigDecimal onePercentOfBiggerRating = BigDecimal.ZERO;
    if (winner.getRating().compareTo(loser.getRating()) == 1) {
      onePercentOfBiggerRating = winner.getRating().scaleByPowerOfTen(-2);
      return loser.getRating().multiply(loser.getRating().divide(onePercentOfBiggerRating).scaleByPowerOfTen(-2));
    } else {
      onePercentOfBiggerRating = loser.getRating().scaleByPowerOfTen(-2);
      return loser.getRating().multiply(winner.getRating().divide(onePercentOfBiggerRating).scaleByPowerOfTen(-2));
    }
    */
  }
}

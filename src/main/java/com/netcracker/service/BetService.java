package com.netcracker.service;

import com.netcracker.dao.BetDAO;
import com.netcracker.model.Bet;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Representation of Match Service.
 */
public class BetService {

  private BetDAO betDAO;

  /**
   * Constructor.
   * Makes an instance of BetService.
   */
  public BetService() {
    betDAO = new BetDAO();
  }

  /**
   * Returns list of all bets from database.
   * @return list of bets.
   */
  public List<Bet> getAll() throws SQLException {
    return  betDAO.getAll();
  }

  /**
   * Returns bet with specified id.
   * @param id is id of Bet.
   * @return an instance of Bet.
   */
  public Bet getById(UUID id) throws SQLException {
    return  betDAO.getById(id);
  }

  /**
   * Updates bet in database.
   * @param bet is instance of Bet.
   */
  public void update(Bet bet) throws SQLException {
    betDAO.update(bet);
  }

  /**
   * Saves bet in database.
   * @param bet is instance of bet to save.
   */
  public void create(Bet bet) throws SQLException {
    betDAO.create(bet);
  }

  /**
   * Deletes bet from database.
   * @param bet is instance of bet to delete.
   */
  public void delete(Bet bet) throws SQLException {
    betDAO.delete(bet.getId());
  }
}

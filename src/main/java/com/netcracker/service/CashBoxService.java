package com.netcracker.service;

import com.netcracker.dao.CashBoxDAO;
import com.netcracker.model.CashBox;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CashBoxService {

  private CashBoxDAO cashBoxDAO;

  /**
   * Constructor.
   * Makes an instance of CashBoxService.
   */
  public CashBoxService() {
    cashBoxDAO = new CashBoxDAO();
  }

  /**
   * Returns list of all cash boxes from database.
   * @return list of cash boxes.
   */
  public List<CashBox> getAll() throws SQLException {
    return  cashBoxDAO.getAll();
  }

  /**
   * Returns cash box with specified id.
   * @param id is id of CashBox.
   * @return an instance of CashBox.
   */
  public CashBox getById(UUID id) throws SQLException {
    return  cashBoxDAO.getById(id);
  }

  /**
   * Updates cash box in database.
   * @param cashBox is instance of CashBox.
   */
  public void update(CashBox cashBox) throws SQLException {
    cashBoxDAO.update(cashBox);
  }

  /**
   * Saves cash box in database.
   * @param cashBox is instance of cash box to save.
   */
  public void create(CashBox cashBox) throws SQLException {
    cashBoxDAO.create(cashBox);
  }

  /**
   * Deletes cash box from database.
   * @param cashBox is instance of cash box to delete.
   */
  public void delete(CashBox cashBox) throws SQLException {
    cashBoxDAO.delete(cashBox.getId());
  }
}

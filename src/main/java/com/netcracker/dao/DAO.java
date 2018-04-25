package com.netcracker.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 Main interface for DAO.
 @param <Entity> This describes type of Entity.
 */
public interface DAO<Entity> {

  /**
   * Returns all instances of Entity from database.
   @return List of all instances of Entity .
   */
  List<Entity> getAll() throws SQLException;

  /**
   * @param id of instance to get.
   * @return instance of Entity.
   */
  Entity getById(UUID id) throws SQLException;

  /**
   * Deletes instance of Entity with specified id.
   * @param id is id of instance to delete.
   */
  void delete(UUID id) throws SQLException;

  /**
   * Inserts instance of Entity in database.
   * @param entity is instance to insert.
   */
  void create(Entity entity) throws SQLException;

  /**
   * Updates instance of Entity in database.
   * @param entity is updating instance.
   */
  void update(Entity entity) throws SQLException;
}

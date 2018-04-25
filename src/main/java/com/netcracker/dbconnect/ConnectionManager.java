package com.netcracker.dbconnect;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionManager {
  private static ConnectionManager dataSource;
  private ComboPooledDataSource comboPooledDataSource;

  private ConnectionManager() {
    try {
      ResourceBundle resource = ResourceBundle.getBundle("database");
      comboPooledDataSource = new ComboPooledDataSource();
      comboPooledDataSource.setDriverClass(System.getenv("DB_DRIVER"));
      comboPooledDataSource.setJdbcUrl(System.getenv("DB_URL"));
      comboPooledDataSource.setUser(System.getenv("DB_USER"));
      comboPooledDataSource.setPassword(System.getenv("DB_PASSWORD"));
    } catch (PropertyVetoException ex1) {
      ex1.printStackTrace();
    }
  }

  public static ConnectionManager getInstance() {
    if (dataSource == null)
      dataSource = new ConnectionManager();
    return dataSource;
  }

  public Connection getConnection() {
    Connection connection = null;
    try {
      connection = comboPooledDataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
}

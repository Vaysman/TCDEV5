package com.netcracker.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Team {

  private UUID id;
  private String teamName;
  private BigDecimal rating;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public BigDecimal getRating() {
    return rating;
  }

  public void setRating(BigDecimal rating) {
    this.rating = rating;
  }

  public void increaseRating(BigDecimal term) {
    this.rating = this.rating.add(term);
  }

  public void decreaseRating(BigDecimal subtrahend) {
    this.rating = this.rating.subtract(subtrahend);
  }
}

package com.netcracker.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Bet {

  private UUID id;
  private UUID userId;
  private UUID matchId;
  private BigDecimal coefficient;
  private BigDecimal betSum;
  private UUID teamId;

  public void setBetSum(BigDecimal betSum) {
    this.betSum = betSum;
  }

  public BigDecimal getCoefficient() {
    return coefficient;
  }

  public void setTeamId(UUID teamId) {
    this.teamId = teamId;
  }

  public UUID getTeamId() {
    return teamId;
  }

  public void setCoefficient(BigDecimal coefficient) {
    this.coefficient = coefficient;
  }


  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public UUID getMatchId() {
    return matchId;
  }

  public void setMatchId(UUID matchId) {
    this.matchId = matchId;
  }

    /* public void setCoeff(String coeff) {
        this.coefficient = new BigDecimal(coeff);
    }

    public void setBetSum(String betSum) throws InvalidBetSumException{

        if (Double.parseDouble(betSum)<0){
            throw new  InvalidBetSumException();
        }
        this.betSum = new BigDecimal(betSum);
    }*/

  public BigDecimal getBetSum() {
    return betSum;
  }

}

package com.netcracker.model;

import java.util.*;

/**
 * Representation of Match.
 */
public class Match {

  private UUID id;
  private MatchState matchState;
  private Competition competition;
  private Date date;
  private Map<UUID, Integer> competitorsAndScores = new HashMap<>(2);

  public Match() {
    matchState = MatchState.OPENED;
  }

  public void setCompetitorsAndScores(UUID teamId, Integer score) {
    competitorsAndScores.put(teamId, score);
  }

  public Map<UUID, Integer> getCompetitorsAndScores() {
    return competitorsAndScores;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

  public Competition getCompetition() {
    return competition;
  }

  public Set<UUID> getCompetitorsIDs() {
    return competitorsAndScores.keySet();
  }

  public Collection<Integer> getCompetitorsScores() {
    return competitorsAndScores.values();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public MatchState getMatchState() {
    return matchState;
  }

  public void setMatchState(MatchState matchState) {
    this.matchState = matchState;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Match)) {
      return false;
    }
    Match match = (Match) obj;
    return this.id.equals(match.id) && this.matchState.equals(match.matchState) && this.date.equals(match.date);
  }
}

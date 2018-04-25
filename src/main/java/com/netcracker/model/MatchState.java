package com.netcracker.model;

/**
 States which can be applied to Match.
 */
public enum MatchState {
  /**
   Match is closed.
   No one can bet on this match
   */
  CLOSED,

  /**
   Match is opened.
   */
  OPENED
}

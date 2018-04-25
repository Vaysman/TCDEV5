package com.netcracker.model;

import java.util.Date;
import java.util.UUID;

/**
 Representation of Comment.
 */
public class Comment {

  private UUID id;
  private UUID userId;
  private UUID matchId;
  private String message;
  private Date date;

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getDate() {
    return date;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Comment)) {
      return false;
    }
    Comment comment = (Comment)obj;
    return this.id.equals(comment.id) && this.message.equals(comment.message) && this.matchId.equals(comment.matchId);
  }
}

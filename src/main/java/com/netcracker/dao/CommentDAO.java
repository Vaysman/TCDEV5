package com.netcracker.dao;

import com.netcracker.dbconnect.ConnectionManager;
import com.netcracker.model.Comment;
import com.netcracker.model.Match;
import com.netcracker.model.MatchState;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Representation of DAO for Comment.
 */
public class CommentDAO extends AbstractEAVDAO <Comment> {

  public List<Comment> getAll() {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<Comment> commentList = new LinkedList<Comment>();
    try {
      Statement statement = connection.createStatement();
      statement.executeQuery(SQLQueries.GET_ALL_COMMENTS);
      ResultSet result = statement.getResultSet();
      while (result.next()) {
        UUID commentId = UUID.fromString(result.getString("id"));
        Map<String, String> commentAttributes = getAttributesAndValuesOfObjectById(commentId, connection);

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setUserId(UUID.fromString(commentAttributes.get("user_id")));
        comment.setMatchId(UUID.fromString(commentAttributes.get("match_id")));
        comment.setMessage(commentAttributes.get("message"));
        commentList.add(comment);
      }
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return commentList;
  }

  public Comment getById(UUID id) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    Comment comment = new Comment();
    try {
      comment.setId(id);
      comment.setMatchId(UUID.fromString(getAttributeValue("match_id", id, connection)));
      comment.setUserId(UUID.fromString(getAttributeValue("user_id", id, connection)));
      comment.setMessage(getAttributeValue("message", id, connection));
      connection.close();
    } catch (SQLException exception) {

    }
    return comment;
  }

  public List<Comment> getByMatchId(UUID matchId) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    List<Comment> commentList = new LinkedList<Comment>();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.GET_COMMENT_BY_MATCH_ID, connection);
      preparedStatement.setString(1, matchId.toString());
      preparedStatement.execute();
      ResultSet result = preparedStatement.getResultSet();
      while (result.next()) {
        Comment comment = new Comment();
        comment.setId(UUID.fromString(result.getString("obj_id")));
        comment.setMatchId(matchId);
        comment.setUserId(UUID.fromString(getAttributeValue("user_id", comment.getId(), connection)));
        comment.setMessage(getAttributeValue("message", comment.getId(), connection));
        commentList.add(comment);
      }
      connection.close();
    } catch (SQLException exception) {

    }
    return commentList;
  }

  public void delete(UUID id) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.DELETE_COMMENT_BY_ID, connection);
      preparedStatement.setString(1, id.toString());
      preparedStatement.executeUpdate();
      connection.close();
    } catch (SQLException exception) {

    }
  }

  public void create(Comment comment) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      PreparedStatement preparedStatement = this.getPreparedStatement(SQLQueries.CREATE_COMMENT, connection);
      preparedStatement.setString(1, comment.getId().toString());
      preparedStatement.executeUpdate();
      this.setAttributeValue("match_id", comment.getMatchId().toString(), UUID.randomUUID(), comment.getId(), connection);
      this.setAttributeValue("user_id", comment.getUserId().toString(), UUID.randomUUID(), comment.getId(), connection);
      this.setAttributeValue("message", comment.getMessage(), UUID.randomUUID(), comment.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }

  public void update(Comment comment) {
    Connection connection = ConnectionManager.getInstance().getConnection();
    try {
      this.updateAttributeValue("match_id", comment.getMatchId().toString(), comment.getId(), connection);
      this.updateAttributeValue("user_id", comment.getUserId().toString(),  comment.getId(), connection);
      this.updateAttributeValue("message", comment.getMessage(), comment.getId(), connection);
      connection.close();
    } catch (SQLException exception) {

    }
  }
}

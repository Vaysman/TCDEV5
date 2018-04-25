package com.netcracker.service;

import com.netcracker.model.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface CommentService extends ServiceInterface<Comment> {
    void addComment(Comment comment) throws SQLException;

    List<Comment> getCommentsToMatch(UUID matchId) throws SQLException;

    void deleteComment(UUID commentId) throws SQLException;
}

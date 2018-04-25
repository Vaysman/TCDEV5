package com.netcracker.service;

import com.netcracker.dao.CommentDAO;
import com.netcracker.model.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CommentSeriveImpl implements CommentService {

    private CommentDAO commentDao = new CommentDAO();

    public void addComment(Comment comment) throws SQLException {
        commentDao.create(comment);
    }

    public List<Comment> getCommentsToMatch(UUID matchId) throws SQLException {
        return commentDao.getByMatchId(matchId);
    }

    public void deleteComment(UUID commentId) throws SQLException {
        commentDao.delete(commentId);
    }
}

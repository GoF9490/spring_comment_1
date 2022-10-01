package comment.crud.service;

import comment.crud.repository.Comment;

import java.util.List;


public interface CommentService {

    Long createComment(Comment comment);
    List<Comment> readAllComment();
    Long updateComment(Comment comment);
    Long deleteComment(Comment comment);

}

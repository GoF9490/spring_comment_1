package comment.crud.service;

import comment.crud.repository.Comment;

import java.util.List;


public interface CommentService {

    Long createComment(String writer, String content);
    List<Comment> readAllComment();
    Long updateComment(Long id, String content);
    Long deleteComment(Long id);

}

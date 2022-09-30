package comment.crud.repository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    Optional<Comment> findByWriter(String writer);
    List<Comment> findAll();
    Comment update(Comment comment);
}

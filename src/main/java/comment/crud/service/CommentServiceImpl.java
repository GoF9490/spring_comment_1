package comment.crud.service;

import comment.crud.repository.Comment;
import comment.crud.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 생성자
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Long createComment(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    @Override
    public List<Comment> readAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Long updateComment(Comment comment) {
        commentRepository.update(comment);
        return comment.getId();
    }

    @Override
    public Long deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.setDeleted(true);
        commentRepository.update(comment);
        return id;
    }
}

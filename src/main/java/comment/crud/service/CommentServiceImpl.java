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
    public Long createComment(String writer, String content) {
        Comment comment = new Comment();

        comment.setWriter(writer);
        comment.setContent(content);
        commentRepository.save(comment);

        return comment.getId();
    }

    @Override
    public List<Comment> readAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Long updateComment(Long id, String content) {
        Optional<Comment> opt = commentRepository.findById(id);

        if (opt.isPresent()) {
            Comment comment = opt.get();
            comment.setContent(content);
            commentRepository.update(comment);

            return comment.getId();
        } else {
            return -1L;
        }
    }

    @Override
    public Long deleteComment(Long id) {
        Optional<Comment> opt = commentRepository.findById(id);

        if (opt.isPresent()) {
            Comment comment = opt.get();
            comment.setDeleted(true);
            commentRepository.update(comment);

            return comment.getId();
        } else {
            return -1L;
        }
    }
}

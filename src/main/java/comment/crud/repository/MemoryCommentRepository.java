package comment.crud.repository;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryCommentRepository implements CommentRepository{

    private static Map<Long, Comment> commentBox = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Comment save(Comment comment) {
        comment.setId(++sequence);
        commentBox.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(commentBox.get(id));
    }

    @Override
    public Optional<Comment> findByWriter(String writer) {
        return commentBox.values().stream()
                .filter(comment -> comment.getWriter().equals(writer))
                .findAny();
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(commentBox.values());
    }

    @Override
    public Comment update(Comment comment) {
        commentBox.put(comment.getId(), comment);
        return comment;
    }

    public void clear() { // 테스트용
        commentBox.clear();
        sequence = 0L;
    }
}

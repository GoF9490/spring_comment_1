package comment.crud.service;

import comment.crud.repository.Comment;
import comment.crud.repository.MemoryCommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CommentServiceImplTest {

    MemoryCommentRepository repository = new MemoryCommentRepository();
    CommentService service = new CommentServiceImpl(repository);

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void writeComment() {

        //given
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");

        //when
        Long id = service.createComment(comment);

        //then
        assertThat(id).isEqualTo(1);

        Optional<Comment> admin = repository.findByWriter("admin");
        assertThat(admin.get().getContent()).isEqualTo("hello world");
    }

    @Test
    public void readAllComment() {

        //given
        Comment comment1 = new Comment();
        comment1.setWriter("admin");
        comment1.setContent("hello");
        repository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setWriter("writer");
        comment2.setContent("world");
        repository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setWriter("admin");
        comment3.setContent("hello world");
        repository.save(comment3);

        //when
        List<Comment> list = service.readAllComment();

        //then
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void updateComment() {

        //given
        Comment comment1 = new Comment();
        comment1.setWriter("admin");
        comment1.setContent("hello");
        repository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setWriter("writer");
        comment2.setContent("world");
        repository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setWriter("admin");
        comment3.setContent("hello world");
        repository.save(comment3);

        comment2.setContent("welcome");

        //when
        Long update = service.updateComment(comment2);

        //then
        assertThat(update).isEqualTo(2);

        Comment comment = repository.findById(2L).get();
        assertThat(comment.getWriter()).isEqualTo("writer");
        assertThat(comment.getContent()).isEqualTo("welcome");
    }

    @Test
    public void deleteComment() {

        //given
        Comment comment1 = new Comment();
        comment1.setWriter("admin");
        comment1.setContent("hello");
        repository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setWriter("writer");
        comment2.setContent("world");
        repository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setWriter("admin");
        comment3.setContent("hello world");
        repository.save(comment3);

        //when
        Long delete = service.deleteComment(comment2);

        //then
        assertThat(delete).isEqualTo(2);

        Comment comment = repository.findById(2L).get();
        assertThat(comment.getDeleted()).isEqualTo(true);
    }
}
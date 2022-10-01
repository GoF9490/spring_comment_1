package comment.crud.service;

import comment.crud.repository.Comment;
import comment.crud.repository.MemoryCommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

        // when
        Long id = service.createComment("admin", "hello world");

        // then
        assertThat(id).isEqualTo(1);
    }

    @Test
    public void readAllComment() {

        //given
        service.createComment("admin", "hello world");
        service.createComment("admin", "hello");
        service.createComment("writer", "world");

        //when
        List<Comment> list = service.readAllComment();

        //then
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    public void updateComment() {

        //given
        service.createComment("admin", "hello world");
        service.createComment("admin", "hello");
        Long id = service.createComment("writer", "world");

        //when
        Long update = service.updateComment(id, "welcome");

        //then
        assertThat(update).isEqualTo(3);

        Comment comment = repository.findById(id).get();
        assertThat(comment.getWriter()).isEqualTo("writer");
        assertThat(comment.getContent()).isEqualTo("welcome");
    }

    @Test
    public void deleteComment() {

        //given
        service.createComment("admin", "hello world");
        service.createComment("admin", "hello");
        Long id = service.createComment("writer", "world");

        //when
        Long delete = service.deleteComment(id);

        //then
        assertThat(delete).isEqualTo(3);

        Comment comment = repository.findById(id).get();
        assertThat(comment.getDeleted()).isEqualTo(true);
    }
}
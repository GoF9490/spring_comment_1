package comment.crud.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryCommentRepositoryTest {

    MemoryCommentRepository repository = new MemoryCommentRepository();

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {

        //GIVEN
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");

        //WHEN
        Comment save = repository.save(comment);

        //THEN
        assertThat(save.getId()).isEqualTo(1);
        assertThat(comment.getWriter()).isEqualTo(save.getWriter());
        assertThat(comment.getContent()).isEqualTo(save.getContent());
        //System.out.println("id = " + save.getId() + " / writer = "
        //        + save.getWriter() + " / content = " + save.getContent());
    }

    @Test
    public void findById() {
        //GIVEN
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");
        Comment comment1 = repository.save(comment);

        //WHEN
        Comment comment2 = repository.findById(comment1.getId()).get();

        //THEN
        assertThat(comment2.getId()).isEqualTo(comment1.getId());
        assertThat(comment2.getWriter()).isEqualTo(comment1.getWriter());
        assertThat(comment2.getContent()).isEqualTo(comment1.getContent());
    }

    @Test
    public void findByIdFail() {
        //GIVEN
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");

        //WHEN
        Comment comment1 = repository.save(comment);

        //THEN
        assertThrows(NoSuchElementException.class, () -> {
            Comment comment2 = repository.findById(5L).get();
        });
    }

    @Test
    public void findByWriter() {
        //GIVEN
        Comment comment1 = new Comment();
        comment1.setWriter("admin1");
        comment1.setContent("hello world1");
        repository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setWriter("admin2");
        comment2.setContent("hello world2");
        repository.save(comment2);

        //WHEN
        Comment find = repository.findByWriter("admin2").get();

        //THEN
        assertThat(find.getId()).isEqualTo(2);
        assertThat(find.getWriter()).isEqualTo("admin2");
        assertThat(find.getContent()).isEqualTo("hello world2");
    }

    @Test
    public void findByWriterFail() {
        //GIVEN
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");

        //WHEN
        Comment comment1 = repository.save(comment);

        //THEN
        assertThrows(NoSuchElementException.class, () -> {
            Comment comment2 = repository.findByWriter("null").get();
        });
    }

    @Test
    public void findAll() {
        //GIVEN
        Comment comment1 = new Comment();
        comment1.setWriter("admin1");
        comment1.setContent("hello world1");
        repository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setWriter("admin2");
        comment2.setContent("hello world2");
        repository.save(comment2);

        //WHEN
        List<Comment> list = repository.findAll();

        //then
        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    public void update() {
        //GIVEN
        Comment comment = new Comment();
        comment.setWriter("admin");
        comment.setContent("hello world");
        Comment save = repository.save(comment);

        //WHEN
        Comment updateBefore = repository.findById(save.getId()).get();
        updateBefore.setContent("welcome");
        repository.save(updateBefore);
        Comment updateAfter = repository.findById(save.getId()).get();

        //THEN
        assertThat(updateAfter.getContent()).isEqualTo("welcome");
    }

}
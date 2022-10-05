package comment.crud.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {

    private Long id;
    private String writer;
    private String content;
    private Boolean delete = false;

}

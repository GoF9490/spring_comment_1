package comment.crud.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

    private String id = "0";
    private String writer = "";
    private String content = "";
}

/**
 * post 방식으로 받아올 데이터들을 저장할 형식(폼).
 * post 방식으로 보낼 데이터들과 이름을 일치시켜 줘야함.
 */

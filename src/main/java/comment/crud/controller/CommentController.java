package comment.crud.controller;

import comment.crud.repository.Comment;
import comment.crud.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/")
    public String home(Model model) {
        List<Comment> comments = commentService.readAllComment();
        model.addAttribute("comments", comments);
        return "home";
    }

    @PostMapping("/create")
    public String create(CommentForm form) {
        Comment comment = new Comment();
        comment.setWriter(form.getWriter());
        comment.setContent(form.getContent());

        commentService.createComment(comment);

        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam Long id, Model model) {
        Comment comment = commentService.suchForId(id);
        // 만약 작성자랑 다르다면 예외처리 추가. (로그인 방식일경우) + 빈 Comment 객체는 작성자가 ""이기에 무조건 다름. 접근 권한이 없습니다 같은 키워드로 튕겨내기.
        // if (comment.delete) return ""; // 이미 삭제되었을 경우 예외처리.
        model.addAttribute("id", id);
        model.addAttribute("writer", comment.getWriter());
        model.addAttribute("content", comment.getContent());

        return  "update";
    }

    @PostMapping("/update")
    public String updateProcess(CommentForm form) {
        Comment comment = commentService.suchForId(Long.parseLong(form.getId()));
        if (!comment.getDelete()) {
            comment.setWriter(form.getWriter());
            comment.setContent(form.getContent());
        }

        commentService.updateComment(comment);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(CommentForm form) {
        //commentService.deleteComment(Long.parseLong(form.getId()));

        Comment comment = new Comment();
        comment.setId(Long.parseLong(form.getId()));
        comment.setWriter("");
        comment.setContent("이 댓글은 삭제되었습니다.");
        comment.setDelete(true);

        commentService.updateComment(comment);

        return "redirect:/";
    }
}

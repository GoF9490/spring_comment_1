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
    public String update(@RequestParam Long id, @RequestParam String writer,
                         @RequestParam String content, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("writer", writer);
        model.addAttribute("content", content);
        return  "update";
    }

    @PostMapping("/update")
    public String updateProcess(CommentForm form) {
        Comment comment = new Comment();
        comment.setId(Long.parseLong(form.getId()));
        comment.setWriter(form.getWriter());
        comment.setContent(form.getContent());

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

        commentService.updateComment(comment);

        return "redirect:/";
    }
}

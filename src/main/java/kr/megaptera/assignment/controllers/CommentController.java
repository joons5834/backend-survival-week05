package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final UpdateCommentService updateCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(GetCommentsService getCommentsService,
                             CreateCommentService createCommentService,
                             UpdateCommentService updateCommentService,
                             DeleteCommentService deleteCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.updateCommentService = updateCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @GetMapping
    List<CommentDto> list(@RequestParam String postId) {
        return getCommentsService.getCommentsDto(postId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CommentDto create(@RequestParam String postId,
                      @RequestBody CommentDto commentDto) {
        return createCommentService.createComment(postId, commentDto);
    }

    @PatchMapping("/{id}")
    CommentDto update(@PathVariable("id") String commentId,
                      @RequestParam String postId,
                      @RequestBody CommentDto requestDto) {
        return updateCommentService.updateComment(commentId, postId,
                requestDto);
    }

    @DeleteMapping("/{id}")
    CommentDto delete(@PathVariable("id") String commentId,
                      @RequestParam String postId) {
        return deleteCommentService.deleteComment(commentId, postId);
    }


}

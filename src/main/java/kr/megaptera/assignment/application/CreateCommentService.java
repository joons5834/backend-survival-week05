package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCommentService {

    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto createComment(String postId, CommentDto commentDto) {
        Comment newComment = new Comment(commentDto.getAuthor(),
                commentDto.getContent());
        commentRepository.save(PostId.of(postId), newComment);
        return new CommentDto(newComment);
    }
}

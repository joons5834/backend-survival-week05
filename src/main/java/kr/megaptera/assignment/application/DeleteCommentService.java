package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDto deleteComment(String id, String postId) {
        Comment commentFound = commentRepository.find(CommentId.of(id),
                PostId.of(postId));

        commentRepository.delete(CommentId.of(id), PostId.of(postId));

        return new CommentDto(commentFound);
    }
}

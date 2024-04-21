package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCommentsService {
    private final CommentRepository commentRepository;

    public GetCommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<CommentDto> getCommentsDto(String postId) {
        return commentRepository.findAll(PostId.of(postId))
                .stream().map(CommentDto::new)
                .toList();
    }
}

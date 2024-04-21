package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {

    private CreateCommentService createCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository);
    }

    @Test
    @DisplayName("댓글 생성 서비스 테스트")
    void create() {
        CommentDto commentDto = new CommentDto(null,
                "post author", "post content");
        String postId = "1";

        CommentDto createdCommentDto =
                createCommentService.createComment(postId, commentDto);

        verify(commentRepository).save(argThat(
                        (PostId id) -> id.equals(PostId.of(postId))),
                argThat(
                        (Comment comment) -> comment.id() != null &&
                                comment.author().equals("post author") &&
                                comment.content().equals("post content")
                ));

        assertThat(createdCommentDto.getId()).isNotBlank();
        assertThat(createdCommentDto.getAuthor()).isEqualTo("post author");
        assertThat(createdCommentDto.getContent()).isEqualTo("post content");
    }
}

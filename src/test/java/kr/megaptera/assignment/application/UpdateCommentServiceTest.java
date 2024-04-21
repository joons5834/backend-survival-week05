package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.CommentId;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


class UpdateCommentServiceTest {
    private UpdateCommentService updateCommentService;
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        commentRepository = mock(CommentRepository.class);
        updateCommentService = new UpdateCommentService(commentRepository);
    }

    @Test
    @DisplayName("updateCommentService")
    void update() {
        CommentDto requestDto = new CommentDto(null,
                null, "new content");
        String id = "1";
        String postId = "1";

        Comment oldComment = spy(new Comment(id,
                "author", "old content"));

        given(commentRepository.find(CommentId.of(id), PostId.of(postId)))
                .willReturn(oldComment);

        CommentDto updatedCommentDto =
                updateCommentService.updateComment(id, postId, requestDto);

        verify(oldComment).update("new content");

        assertThat(updatedCommentDto.getId()).isNotBlank();
        assertThat(updatedCommentDto.getAuthor()).isNotBlank();
        assertThat(updatedCommentDto.getContent()).isEqualTo("new content");
    }
}

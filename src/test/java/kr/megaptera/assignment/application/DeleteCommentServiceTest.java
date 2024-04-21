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
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class DeleteCommentServiceTest {
    private CommentRepository commentRepository;
    private DeleteCommentService deleteCommentService;

    @BeforeEach
    public void setUp() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository);
    }

    @Test
    @DisplayName("deleteCommentServiceTest")
    void delete() {
        String id = "1";
        String strPostId = "2";

        Comment oldComment = spy(new Comment(id,
                "author", "old content"));

        given(commentRepository.find(CommentId.of(id), PostId.of(strPostId)))
                .willReturn(oldComment);

        CommentDto deletedCommentDto =
                deleteCommentService.deleteComment(id, strPostId);

        verify(commentRepository).delete(
                argThat((CommentId commentId) ->
                        commentId.toString().equals(id)),
                argThat((PostId postId) ->
                        postId.toString().equals(strPostId)));

        assertThat(deletedCommentDto.getId())
                .isEqualTo(id);
        assertThat(deletedCommentDto.getAuthor())
                .isNotBlank();
        assertThat(deletedCommentDto.getContent())
                .isNotBlank();
    }
}

package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.CommentDto;
import kr.megaptera.assignment.models.Comment;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetCommentsServiceTest {
    private GetCommentsService getCommentsService;
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        commentRepository = mock(CommentRepository.class);
        getCommentsService = new GetCommentsService(commentRepository);

    }

    @Test
    @DisplayName("post id로 리포지토리에 댓글 목록 받아와 " +
            "commentDto 목록으로 변환")
    void list() {
        String postId = "1";

        given(commentRepository.findAll(PostId.of(postId)))
                .willReturn(List.of(
                        new Comment("1", "author1", "content1"),
                        new Comment("2", "author2", "content2")
                ));

        List<CommentDto> commentDtos = getCommentsService.getCommentsDto(postId);
        assertThat(commentDtos).hasSize(2);
    }

}

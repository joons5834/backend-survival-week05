package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.models.PostId;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePostServiceTest {
    private DeletePostService deletePostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        deletePostService = new DeletePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 삭제")
    void delete() {
        String id = "1";
        PostId postId = PostId.of(id);
        given(postRepository.find(postId))
                .willReturn(new Post(
                        postId,
                        "제목",
                        "작성자",
                        "내용"));

        PostDto deletedPostDto = deletePostService.deletePost(id);
        verify(postRepository).deletePost(postId);

        assertThat(deletedPostDto.getId())
                .isNotNull();
        assertThat(deletedPostDto.getTitle())
                .isNotNull();
        assertThat(deletedPostDto.getAuthor())
                .isNotNull();
        assertThat(deletedPostDto.getContent())
                .isNotNull();
    }
}

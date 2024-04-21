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

class GetPostServiceTest {
    private PostRepository postRepository;
    private GetPostService getPostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        getPostService = new GetPostService(postRepository);
    }

    @Test
    @DisplayName("게시물 조회 - 올바른 ID")
    void detail() {

        String id = "0001POST";
        PostId postId = PostId.of(id);
        given(postRepository.find(postId))
                .willReturn(new Post(
                        postId,
                        "제목",
                        "작성자",
                        "내용"));

        PostDto postDto = getPostService.getPostDto(id);
        assertThat(postDto).isEqualTo(new PostDto(
                id, "제목", "작성자", "내용"
        ));
    }

    @Test
    @DisplayName("게시물 조회 - 없는 ID")
    void detailOfInvalidId() {

        String id = "0001POST";
        PostId postId = PostId.of(id);
        given(postRepository.find(postId))
                .willReturn(null);

        PostDto postDto = getPostService.getPostDto(id);
        assertThat(postDto).isNull();
    }
}

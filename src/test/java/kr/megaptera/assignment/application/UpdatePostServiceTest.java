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
import static org.mockito.Mockito.*;

class UpdatePostServiceTest {
    private PostRepository postRepository;
    private UpdatePostService updatePostService;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        updatePostService = new UpdatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 수정")
    void update() {
        String idForUpdate = "1";

        Post oldPost = spy(new Post(
                PostId.of("0001POST"),
                "old title",
                "author1",
                "old content"
        ));

        given(postRepository.find(PostId.of(idForUpdate)))
                .willReturn(oldPost);

        PostDto updatedPostDto = updatePostService.updatePost(idForUpdate, new PostDto(
                null, "updated title",
                null, "updated content"));

        verify(oldPost).update("updated title", "updated content");

        assertThat(updatedPostDto.getId())
                .isEqualTo("0001POST");
        assertThat(updatedPostDto.getTitle())
                .isEqualTo("updated title");
        assertThat(updatedPostDto.getAuthor())
                .isEqualTo("author1");
        assertThat(updatedPostDto.getContent())
                .isEqualTo("updated content");
    }
}

package kr.megaptera.assignment.application;

import kr.megaptera.assignment.dtos.PostDto;
import kr.megaptera.assignment.models.Post;
import kr.megaptera.assignment.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreatePostServiceTest {
    private CreatePostService createPostService;
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        createPostService = new CreatePostService(postRepository);
    }

    @Test
    @DisplayName("게시물 생성")
    void create() {
        PostDto postDto = new PostDto(null,
                "new post",
                "new author",
                "new content");

        PostDto createdPostDto = createPostService.createPost(postDto);

        verify(postRepository).save(argThat(post ->
                ((Post) post).postId() != null &&
                        ((Post) post).title().equals("new post") &&
                        ((Post) post).author().equals("new author") &&
                        ((Post) post).content().equals("new content")
        ));

        assertThat(createdPostDto.getId())
                .isNotNull();
        assertThat(createdPostDto.getTitle())
                .isEqualTo("new post");
        assertThat(createdPostDto.getAuthor())
                .isEqualTo("new author");
        assertThat(createdPostDto.getContent())
                .isEqualTo("new content");

    }
}

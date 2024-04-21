package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.application.*;
import kr.megaptera.assignment.dtos.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetPostsService getPostsService;
    @MockBean
    private GetPostService getPostService;
    @MockBean
    private CreatePostService createPostService;
    @MockBean
    private UpdatePostService updatePostService;
    @MockBean
    private DeletePostService deletePostService;

    @Test
    @DisplayName("GET /posts")
    void list() throws Exception {
        List<PostDto> mockList = List.of(
                new PostDto("001", "제목", "저자1", "테스트입니다"),
                new PostDto("002", "second", "저자2", "post")
        );
        given(getPostsService.getPostDtos()).willReturn(mockList);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapper.writeValueAsString(mockList)));
    }

    @Test
    @DisplayName("GET /posts/{id} - with correct ID")
    void detailWithCorrectId() throws Exception {
        String id = "1";
        PostDto mockPostDto = new PostDto("001", "제목", "저자1", "테스트입니다");
        given(getPostService.getPostDto(id)).willReturn(mockPostDto);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        mapper.writeValueAsString(mockPostDto)
                ));
    }

    @Test
    @DisplayName("GET /posts/{id} - with incorrect ID")
    void detail() throws Exception {
        String id = "2";
        given(getPostService.getPostDto(id)).willReturn(null);

        mockMvc.perform(get("/posts/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /posts")
    void create() throws Exception {
        String requestJson = """
                {
                    "title": "new post",
                    "author": "new author",
                    "content": "new content"
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        PostDto postDto = objectMapper.readValue(requestJson, PostDto.class);

        PostDto newPostDto = new PostDto(
                "new Id",
                "new post",
                "new author",
                "new content"
        );

        given(createPostService.createPost(postDto))
                .willReturn(newPostDto);

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(newPostDto)
                ));

    }

    @Test
    @DisplayName("PATCH /posts/{id}")
    void update() throws Exception {
        String requestJson = """
                {
                    "title": "updated post",
                    "content": "updated content"
                }
                 """;
        String id = "1";
        ObjectMapper objectMapper = new ObjectMapper();
        PostDto postDto = objectMapper.readValue(requestJson, PostDto.class);

        PostDto updatedPostDto = new PostDto(
                "new Id",
                "updated post",
                "new author",
                "updated content"
        );

        given(updatePostService.updatePost(id, postDto))
                .willReturn(updatedPostDto);

        mockMvc.perform(patch("/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(updatedPostDto)
                ));

    }

    @Test
    @DisplayName("DELETE /posts/{id}")
    void deletePost() throws Exception {
        String id = "1";

        PostDto deletedPostDto = new PostDto(
                "delete test Id",
                "deleted post",
                "delete test author",
                "deleted content"
        );

        given(deletePostService.deletePost(id))
                .willReturn(deletedPostDto);

        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(delete("/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(deletedPostDto)
                ));
    }
}

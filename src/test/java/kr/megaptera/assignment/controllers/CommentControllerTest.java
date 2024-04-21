package kr.megaptera.assignment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.megaptera.assignment.application.CreateCommentService;
import kr.megaptera.assignment.application.DeleteCommentService;
import kr.megaptera.assignment.application.GetCommentsService;
import kr.megaptera.assignment.application.UpdateCommentService;
import kr.megaptera.assignment.dtos.CommentDto;
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

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetCommentsService getCommentsService;
    @MockBean
    private CreateCommentService createCommentService;
    @MockBean
    private DeleteCommentService deleteCommentService;
    @MockBean
    private UpdateCommentService updateCommentService;

    @Test
    @DisplayName("GET /comments?postId=")
    void list() throws Exception {
        String postId = "1";
        List<CommentDto> mockCommentList = List.of(
                new CommentDto("001", "author1", "content1"),
                new CommentDto("002", "author2", "content2")
        );
        given(getCommentsService.getCommentsDto(postId))
                .willReturn(mockCommentList);


        mockMvc.perform(get("/comments?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().
                        json(objectMapper.writeValueAsString(mockCommentList)));
    }

    @Test
    @DisplayName("POST /comments?postId=")
    void create() throws Exception {
        String requestJson = """
                {
                    "author": "post test author",
                    "content": "post test content"
                }
                """;
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDto commentDto = objectMapper.readValue(requestJson,
                CommentDto.class);

        CommentDto newCommentDto = new CommentDto("1",
                "post test author", "post test content");

        String postId = "1";

        given(createCommentService.createComment(postId, commentDto))
                .willReturn(newCommentDto);


        mockMvc.perform(post("/comments?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(newCommentDto)
                ));
    }

    @Test
    @DisplayName("PATCH /comments/{id}?postId={postId}")
    void update() throws Exception {
        String id = "1";
        String postId = "1";

        String requestJson = "{\"" +
                "content\": \"new content\"}";

        CommentDto requestDto = objectMapper.readValue(requestJson,
                CommentDto.class);
        CommentDto updatedCommentDto = new CommentDto(id,
                "author", "new content");

        given(updateCommentService.updateComment(id, postId, requestDto))
                .willReturn(updatedCommentDto);


        mockMvc.perform(patch(
                        "/comments/" + id + "?postId=" + postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(updatedCommentDto)
                ));
    }

    @Test
    @DisplayName("DELETE /comments/{id}?postId={postId}")
    void deleteComment() throws Exception {
        String id = "1";
        String postId = "1";

        CommentDto commentToDelete = new CommentDto(id,
                "delete test author",
                "delete test content");

        given(deleteCommentService.deleteComment(id, postId))
                .willReturn(commentToDelete);


        mockMvc.perform(delete("/comments/" + id + "?postId=" + postId))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(commentToDelete)
                ));
    }

}

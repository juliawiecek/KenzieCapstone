package com.kenzie.appserver.controller;

import com.kenzie.appserver.Exceptions.CommentCreationException;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.CreateCommentRequest;
import com.kenzie.appserver.Exceptions.CommentNotFoundException;
import com.kenzie.appserver.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private CommentService commentService;

    private CommentResponse commentResponse;
    private CreateCommentRequest createCommentRequest;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
        createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setUserId("user123");
        createCommentRequest.setTitle("Sample Title");
        createCommentRequest.setContents("Sample Content");
        createCommentRequest.setEpisodeId("episode123");

        commentResponse = new CommentResponse();
        commentResponse.setCommentId(UUID.randomUUID().toString());
        commentResponse.setUserId("user123");
        commentResponse.setTitle("Sample Title");
        commentResponse.setContents("Sample Content");
        commentResponse.setEpisodeId("episode123");
        commentResponse.setTimestamp("2023-04-05T10:15:30.00Z");
    }

    // happy for Create Comment
    @Test
    void testPostComment_HappyPath() throws Exception {
        given(commentService.createNewComment(any(CreateCommentRequest.class))).willReturn(commentResponse);
        mockMvc.perform(post("/api/comments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"user123\",\"title\":\"Sample Title\",\"contents\":\"Sample Content\",\"episodeId\":\"episode123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user123"));
    }

    // sad for Create Comment
    @Test
    void testPostComment_SadPath() throws Exception {
        given(commentService.createNewComment(any(CreateCommentRequest.class))).willThrow(new CommentCreationException("Failed to create comment"));
        mockMvc.perform(post("/api/comments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"\",\"title\":\"\",\"contents\":\"\",\"episodeId\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    // happy for Get All Comments
    @Test
    void testGetAllComments_happyPath() throws Exception {
        given(commentService.getAllComments()).willReturn(Arrays.asList(commentResponse));
        mockMvc.perform(get("/api/comments/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("user123"));
    }

    // sad for Get All Comments
    @Test
    void testGetAllComments_sadPath() throws Exception {
        given(commentService.getAllComments()).willThrow(new CommentNotFoundException("No comments found"));
        mockMvc.perform(get("/api/comments/all"))
                .andExpect(status().isNotFound());
    }

    // happy for Update Comment
    @Test
    void testUpdateComment_HappyPath() throws Exception {
        given(commentService.updateComment(anyString(), any(CreateCommentRequest.class))).willReturn(commentResponse);
        mockMvc.perform(put("/api/comments/update/" + commentResponse.getCommentId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"user123\",\"title\":\"Updated Title\",\"contents\":\"Updated Content\",\"episodeId\":\"episode123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    // sad for Update Comment
    @Test
    void testUpdateComment_SadPath() throws Exception {
        given(commentService.updateComment(anyString(), any(CreateCommentRequest.class))).willThrow(new CommentNotFoundException("Comment not found"));
        mockMvc.perform(put("/api/comments/update/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"user123\",\"title\":\"Nonexistent\",\"contents\":\"Nonexistent\",\"episodeId\":\"episode123\"}"))
                .andExpect(status().isNotFound());
    }

    // happy for Delete Comment
    @Test
    void testDeleteComment_HappyPath() throws Exception {
        doNothing().when(commentService).deleteComment(anyString());
        mockMvc.perform(delete("/api/comments/" + commentResponse.getCommentId()))
                .andExpect(status().isNoContent());
    }

    // sad path for Delete Comment
    @Test
    void testDeleteComment_SadPath() throws Exception {
        doThrow(new CommentNotFoundException("Comment not found")).when(commentService).deleteComment(anyString());
        mockMvc.perform(delete("/api/comments/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
    // happy for getTopThree Comments
    @Test
    public void getTopThreeComments_HappyPath() throws Exception {
        // setup mock CommentResponse objects
        CommentResponse comment1 = new CommentResponse();
        comment1.setCommentId("1");
        comment1.setUserId("user1");
        comment1.setTitle("Title1");
        comment1.setContents("Content1");
        comment1.setEpisodeId("episode1");
        comment1.setTimestamp("2023-01-01T00:00:00Z");

        CommentResponse comment2 = new CommentResponse();
        comment2.setCommentId("2");
        comment2.setUserId("user2");
        comment2.setTitle("Title2");
        comment2.setContents("Content2");
        comment2.setEpisodeId("episode2");
        comment2.setTimestamp("2023-01-02T00:00:00Z");

        CommentResponse comment3 = new CommentResponse();
        comment3.setCommentId("3");
        comment3.setUserId("user3");
        comment3.setTitle("Title3");
        comment3.setContents("Content3");
        comment3.setEpisodeId("episode3");
        comment3.setTimestamp("2023-01-03T00:00:00Z");

        List<CommentResponse> topComments = Arrays.asList(comment1, comment2, comment3);


        when(commentService.getTopThreeComments()).thenReturn(topComments);

        mockMvc.perform(get("/api/comments/top-three")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].commentId").value("1"))
                .andExpect(jsonPath("$[1].commentId").value("2"))
                .andExpect(jsonPath("$[2].commentId").value("3"));
    }
    // sad for getTopThree Comments
    @Test
    public void getTopThreeComments_SadPath() throws Exception {

        List<CommentResponse> topComments = new ArrayList<>();


        when(commentService.getTopThreeComments()).thenReturn(topComments);


        mockMvc.perform(get("/api/comments/top-three")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}

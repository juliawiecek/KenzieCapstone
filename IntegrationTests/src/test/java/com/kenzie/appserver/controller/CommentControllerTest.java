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

import java.time.Instant;
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
        // set up of sample CommentRequest and commentResponse for each test
        createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setUserId("user123");
        createCommentRequest.setUserName("Sample User Name"); // changed from setTitle to setUserName
        createCommentRequest.setContents("Sample Content");
        createCommentRequest.setEpisodeId("episode123");

        commentResponse = new CommentResponse();
        commentResponse.setCommentId(UUID.randomUUID().toString());
        commentResponse.setUserId("user123");
        commentResponse.setUserName("Sample User Name"); // changed from setTitle to setUserName
        commentResponse.setContents("Sample Content");
        commentResponse.setEpisodeId("episode123");
        commentResponse.setTimestamp(Instant.now().getEpochSecond()); // changed to long type for timestamp
    }

    // happy path for Create Comment
    @Test
    void testPostComment_HappyPath() throws Exception {
        given(commentService.createNewComment(any(CreateCommentRequest.class))).willReturn(commentResponse);
        mockMvc.perform(post("/api/comments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"user123\",\"userName\":\"Sample User Name\",\"contents\":\"Sample Content\",\"episodeId\":\"episode123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user123"))
                .andExpect(jsonPath("$.userName").value("Sample User Name"));
    }

    // sad path for Create Comment
    @Test
    void testPostComment_SadPath() throws Exception {

        given(commentService.createNewComment(any(CreateCommentRequest.class))).willThrow(new CommentCreationException("Failed to create comment"));
        mockMvc.perform(post("/api/comments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\":\"\",\"userName\":\"\",\"contents\":\"\",\"episodeId\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    // happy path for Get All Comments
    @Test
    void testGetAllComments_happyPath() throws Exception {
        given(commentService.getAllComments()).willReturn(Arrays.asList(commentResponse));
        mockMvc.perform(get("/api/comments/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId").value("user123"))
                .andExpect(jsonPath("$[0].userName").value("Sample User Name"));
    }

    // sad path for Get All Comments
    @Test
    void testGetAllComments_sadPath() throws Exception {
        given(commentService.getAllComments()).willThrow(new CommentNotFoundException("No comments found"));
        mockMvc.perform(get("/api/comments/all"))
                .andExpect(status().isNotFound());
    }

    // appy path for Update Comment
    @Test
    void testUpdateComment_HappyPath() throws Exception {
        CommentResponse updatedCommentResponse = new CommentResponse();
        updatedCommentResponse.setCommentId(commentResponse.getCommentId());
        updatedCommentResponse.setUserId("user123");
        updatedCommentResponse.setUserName("Updated User Name");
        updatedCommentResponse.setContents("Updated Content");
        updatedCommentResponse.setEpisodeId("episode123");
        updatedCommentResponse.setTimestamp(commentResponse.getTimestamp());

        given(commentService.updateComment(anyString(), anyString())).willReturn(updatedCommentResponse);

        mockMvc.perform(put("/api/comments/update/" + commentResponse.getCommentId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contents\":\"Updated Content\"}")) // Simplified content body for clarity
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Updated User Name"));
    }
    // sad path for Update Comment
    @Test
    void testUpdateComment_SadPath() throws Exception {
        given(commentService.updateComment(anyString(), anyString())).willThrow(new CommentNotFoundException("Comment not found"));

        mockMvc.perform(put("/api/comments/update/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contents\":\"Nonexistent\"}"))
                .andExpect(status().isNotFound());
    }

    // happy path for Delete Comment
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
        mockMvc.perform(delete("/api/comments/" + UUID.randomUUID().toString()))
                .andExpect(status().isNotFound());
    }
    // happy for getTopThree Comments
    @Test
    void getTopThreeComments_HappyPath() throws Exception {
        List<CommentResponse> topComments = new ArrayList<>();

        // mock three different comments
        CommentResponse comment1 = new CommentResponse();
        comment1.setCommentId(UUID.randomUUID().toString());
        comment1.setUserId("user1");
        comment1.setUserName("User One");
        comment1.setContents("Content One");
        comment1.setEpisodeId("episode1");
        comment1.setTimestamp(Instant.now().getEpochSecond());

        CommentResponse comment2 = new CommentResponse();
        comment2.setCommentId(UUID.randomUUID().toString());
        comment2.setUserId("user2");
        comment2.setUserName("User Two");
        comment2.setContents("Content Two");
        comment2.setEpisodeId("episode2");
        comment2.setTimestamp(Instant.now().getEpochSecond());

        CommentResponse comment3 = new CommentResponse();
        comment3.setCommentId(UUID.randomUUID().toString());
        comment3.setUserId("user3");
        comment3.setUserName("User Three");
        comment3.setContents("Content Three");
        comment3.setEpisodeId("episode3");
        comment3.setTimestamp(Instant.now().getEpochSecond());

        topComments.add(comment1);
        topComments.add(comment2);
        topComments.add(comment3);

        given(commentService.getTopThreeComments()).willReturn(topComments);

        mockMvc.perform(get("/api/comments/top-three")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].userId").value(comment1.getUserId()))
                .andExpect(jsonPath("$[0].userName").value(comment1.getUserName()))
                .andExpect(jsonPath("$[1].userId").value(comment2.getUserId()))
                .andExpect(jsonPath("$[1].userName").value(comment2.getUserName()))
                .andExpect(jsonPath("$[2].userId").value(comment3.getUserId()))
                .andExpect(jsonPath("$[2].userName").value(comment3.getUserName()));
    }

    // sad path for Get Top Three Comments
    @Test
    void getTopThreeComments_SadPath() throws Exception {
        // setup for when there are no top comments
        List<CommentResponse> topComments = new ArrayList<>();

        given(commentService.getTopThreeComments()).willReturn(topComments);

        mockMvc.perform(get("/api/comments/top-three")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // an empty list should still return a 200 OK status
                .andExpect(jsonPath("$", hasSize(0))); // expecting the array to be empty
    }

}

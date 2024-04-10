package com.kenzie.appserver.service;


import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.CreateCommentRequest;
import com.kenzie.appserver.Exceptions.CommentNotFoundException;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CommentServiceTest {
    private CommentRepository commentRepository;

    private CommentService commentService;

    private CreateCommentRequest createCommentRequest;
    private CommentRecord commentRecord;
    private String commentId;

    @BeforeEach
    void setUp() {
        //test objects are here
        commentRepository=mock(CommentRepository.class);
        commentService=new CommentService(commentRepository);
        commentId = UUID.randomUUID().toString();
        createCommentRequest = new CreateCommentRequest();
        createCommentRequest.setUserId("user123");
        createCommentRequest.setUserName("User Name");
        createCommentRequest.setContents("Test Content");
        createCommentRequest.setEpisodeId("episode123");

        commentRecord = new CommentRecord();
        commentRecord.setCommentId(commentId);
        commentRecord.setUserId("user123");
        commentRecord.setUserName("User Name");
        commentRecord.setContents("Test Content");
        commentRecord.setEpisodeId("episode123");
        commentRecord.setTimestamp(Instant.now().getEpochSecond());
    }

    @Test
    void createNewComment_createsAndReturnsComment() {
        when(commentRepository.save(any(CommentRecord.class))).thenReturn(commentRecord);

        CommentResponse response = commentService.createNewComment(createCommentRequest);

        assertNotNull(response);
        assertEquals(createCommentRequest.getUserId(), response.getUserId());
        assertEquals(createCommentRequest.getUserName(), response.getUserName());
        assertEquals(createCommentRequest.getContents(), response.getContents());
        assertEquals(createCommentRequest.getEpisodeId(), response.getEpisodeId());

        verify(commentRepository).save(any(CommentRecord.class));
    }

    @Test
    void getAllComments_returnsListOfComments() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(commentRecord));

        List<CommentResponse> responses = commentService.getAllComments();

        assertFalse(responses.isEmpty());
        assertEquals(1, responses.size());
        assertEquals(commentRecord.getUserId(), responses.get(0).getUserId());

        verify(commentRepository).findAll();
    }

    @Test
    void getTopThreeComments_returnsTopThreeComments() {
        CommentRecord anotherRecord = new CommentRecord();
        anotherRecord.setCommentId(UUID.randomUUID().toString());
        anotherRecord.setTimestamp(Instant.now().getEpochSecond()- 3600); // 1 hour earlier

        when(commentRepository.findAll()).thenReturn(Arrays.asList(commentRecord, anotherRecord));

        List<CommentResponse> responses = commentService.getTopThreeComments();

        assertFalse(responses.isEmpty());
        assertTrue(responses.size() <= 3); // make sure no more than 3 comments are returned
        assertEquals(commentRecord.getCommentId(), responses.get(0).getCommentId()); // newest first

        verify(commentRepository).findAll();
    }

    @Test
    void deleteComment_whenCommentExists_deletesComment() {
        when(commentRepository.existsById(commentId)).thenReturn(true);

        assertDoesNotThrow(() -> commentService.deleteComment(commentId));

        verify(commentRepository).deleteById(commentId);
    }

    @Test
    void deleteComment_whenCommentDoesNotExist_throwsException() {
        when(commentRepository.existsById(commentId)).thenReturn(false);

        assertThrows(CommentNotFoundException.class, () -> commentService.deleteComment(commentId));

        verify(commentRepository, never()).deleteById(anyString());
    }

    @Test
    void updateComment_whenCommentExists_updatesAndReturnsComment() {
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(commentRecord));
        when(commentRepository.save(any(CommentRecord.class))).thenReturn(commentRecord);

        CommentResponse updatedResponse = commentService.updateComment(commentId, String.valueOf(createCommentRequest));

        assertNotNull(updatedResponse);
        assertEquals(createCommentRequest.getUserName(), updatedResponse.getUserName());

        verify(commentRepository).save(any(CommentRecord.class));
    }

    @Test
    void updateComment_whenCommentDoesNotExist_throwsException() {
        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.updateComment(commentId, String.valueOf(createCommentRequest)));

        verify(commentRepository, never()).save(any(CommentRecord.class));
    }
}
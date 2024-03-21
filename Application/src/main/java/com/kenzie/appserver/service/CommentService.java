package com.kenzie.appserver.service;

import com.kenzie.appserver.Exceptions.CommentNotFoundException;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.CreateCommentRequest;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponse createNewComment(CreateCommentRequest createCommentRequest) {
        CommentRecord record = new CommentRecord();
        record.setCommentId(UUID.randomUUID().toString());
        record.setUserId(createCommentRequest.getUserId());
        record.setUserName(createCommentRequest.getUserName());
        record.setContents(createCommentRequest.getContents());
        record.setEpisodeId(createCommentRequest.getEpisodeId());
        record.setTimestamp(Instant.now().toString());
        commentRepository.save(record);

        return mapToCommentResponse(record);
    }

    public List<CommentResponse> getAllComments() {
        List<CommentResponse> responses = new ArrayList<>();
        commentRepository.findAll().forEach(record -> responses.add(mapToCommentResponse(record)));
        return responses;
    }

    public List<CommentResponse> getTopThreeComments() {
        Iterable<CommentRecord> allComments = commentRepository.findAll();

        // Convert Iterable to Stream for sorting and limiting
        List<CommentResponse> sortedComments = StreamSupport.stream(allComments.spliterator(), false)
                .sorted(Comparator.comparing(CommentRecord::getTimestamp).reversed()) // Make sure CommentRecord has getTimestamp
                .limit(3) // Keep only the top three
                .map(this::mapToCommentResponse) // convert each Record to Response
                .collect(Collectors.toList());

        return sortedComments;
    }
    public boolean deleteComment(String commentId) {
        // add exception if comment does not exist.
        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotFoundException(commentId);
        }
        commentRepository.deleteById(commentId);
        return true;
    }



    public CommentResponse updateComment(String commentId, CreateCommentRequest request) {
        return commentRepository.findById(commentId).map(existingRecord -> {
            existingRecord.setUserName(request.getUserName());
            existingRecord.setContents(request.getContents());
            commentRepository.save(existingRecord);
            return mapToCommentResponse(existingRecord);
        }).orElseThrow(() -> new CommentNotFoundException(commentId));
    }
    // method to convert CommentRecord to CommentResponse
    private CommentResponse mapToCommentResponse(CommentRecord record) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(record.getCommentId());
        response.setUserId(record.getUserId());
        response.setUserName(record.getUserName());
        response.setContents(record.getContents());
        response.setEpisodeId(record.getEpisodeId());
        response.setTimestamp(record.getTimestamp());
        return response;
    }
}
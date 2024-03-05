package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.CreateCommentRequest;
import com.kenzie.appserver.repositories.CommentRepository;
import com.kenzie.appserver.repositories.model.CommentRecord;

import java.time.LocalDateTime;

import static java.util.UUID.randomUUID;

public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponse createNewComment(CreateCommentRequest createCommentRequest) {

        CommentRecord record = new CommentRecord();
        record.setCommentId(randomUUID().toString());
        record.setUserId(createCommentRequest.getUserId());
        record.setTitle(createCommentRequest.getTitle());
        record.setContents(createCommentRequest.getContents());
        record.setEpisodeId(createCommentRequest.getEpisodeId());
        record.setTimestamp(LocalDateTime.now().toString());
        commentRepository.save(record);

        CommentResponse response = new CommentResponse();
        response.setCommentId(record.getCommentId());
        response.setUserId(record.getUserId());
        response.setTitle(record.getTitle());
        response.setContents(record.getContents());
        response.setEpisodeId(record.getEpisodeId());
        response.setTimestamp(record.getTimestamp());

        return response;
    }
}

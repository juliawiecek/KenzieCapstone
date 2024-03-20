package com.kenzie.appserver.controller;
import com.kenzie.appserver.controller.model.CommentResponse;
import com.kenzie.appserver.controller.model.CreateCommentRequest;
import com.kenzie.appserver.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments") // base path for comments-related actions
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Endpoint for creating a new comment
    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        CommentResponse response = commentService.createNewComment(request);
        return ResponseEntity.ok(response);
    }

    // Endpoint for retrieving all comments
    @GetMapping("/all")
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentResponse> responses = commentService.getAllComments();
        return ResponseEntity.ok(responses);
    }

    // Endpoint for retrieving the top three comments
    @GetMapping("/top-three")
    public ResponseEntity<List<CommentResponse>> getTopThreeComments() {
        List<CommentResponse> responses = commentService.getTopThreeComments();
        return ResponseEntity.ok(responses);
    }

    // Endpoint for deleting a comment by ID
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
//        boolean isDeleted = commentService.deleteComment(commentId);
//        if (isDeleted) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @DeleteMapping("/{commentId}") // added exception handling indirectly through service class created previously.
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId); // if commentId is not found deleteComment will throw CommentNotFoundException.
        return ResponseEntity.noContent().build();
    }

    // Endpoint for updating a comment by ID
//    @PutMapping("/update/{commentId}")
//    public ResponseEntity<CommentResponse> updateComment(@PathVariable String commentId, @RequestBody CreateCommentRequest request) {
//        CommentResponse updatedComment = commentService.updateComment(commentId, request);
//        if (updatedComment != null) {
//            return ResponseEntity.ok(updatedComment);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    @PutMapping("/update/{commentId}") // added exception handling indirectly through service class created.
    public ResponseEntity<CommentResponse> updateComment(@PathVariable String commentId, @RequestBody CreateCommentRequest request) {
        CommentResponse updatedComment = commentService.updateComment(commentId, request); // if commentId is not found, updateComment will throw CommentNotFoundException.
        return ResponseEntity.ok(updatedComment);
    }

    // @PutMapping("/update/{commentId}/likes") // not sure what to put here for the PutMapping params
    public ResponseEntity<CommentResponse> likeComment;


}
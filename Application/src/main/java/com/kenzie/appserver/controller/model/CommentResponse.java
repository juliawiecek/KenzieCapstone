package com.kenzie.appserver.controller.model;

public class CommentResponse {
    private String commentId;
    private String userId;
    private String title;
    private String contents;
    private String episodeId;
    private String timestamp;

    public String getContents() {
        return contents;
    }

    public String getEpisodeId() {
        return episodeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

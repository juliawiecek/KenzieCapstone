package com.kenzie.appserver.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
    private String commentId;
    private String userId;
    private String userName;
    private String contents;
    private String episodeId;
    private long timestamp;
    private int likes;

    public Comment(String commentId, String userId, String userName, String contents, String episodeId, long timestamp, int likes) {
        this.commentId = commentId;
        this.userId = userId;
        this.userName = userName;
        this.contents = contents;
        this.episodeId = episodeId;
        this.timestamp = timestamp;
        this.likes = likes;
    }

    public String getContents() {
            return contents;
        }

    public String getEpisodeId() {
            return episodeId;
        }

    public String getUserId() {
            return userId;
        }

    public String getUserName() {
        return userName;
    }

    public String getCommentId() {
            return commentId;
        }

    public long getTimestamp() {
        return timestamp;
    }

    public int getLikes() {
        return likes;
    }

}

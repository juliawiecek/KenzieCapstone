package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CreateCommentRequest {
    @NotEmpty
    @JsonProperty("userId")
    private String userId;
    @NotEmpty
    @JsonProperty("userName")
    private String userName;
    @NotEmpty
    @JsonProperty("contents")
    private String contents;
    @NotEmpty
    @JsonProperty("episodeId")
    private String episodeId;
    @NotEmpty
    @JsonProperty("likes")
    private int likes;

    public CreateCommentRequest() {

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

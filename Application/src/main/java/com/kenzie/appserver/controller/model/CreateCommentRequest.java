package com.kenzie.appserver.controller.model;

public class CreateCommentRequest {
    private String userId;
    private String userName;
    private String contents;
    private String episodeId;

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

package com.kenzie.appserver.controller.model;

public class CreateCommentRequest {
    private String userId;
    private String title;
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

    public String getTitle() {
        return title;
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
}

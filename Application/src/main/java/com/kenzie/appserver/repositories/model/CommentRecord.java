package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import nonapi.io.github.classgraph.json.Id;

import java.util.Objects;

@DynamoDBTable(tableName = "CommentTable")
public class CommentRecord {
    @Id
    private String commentId;
    private String userId;
    private String userName;
    private String contents;
    private String episodeId;
    private long timestamp;

    private int likes;

    @DynamoDBHashKey(attributeName = "CommentId")
    public String getCommentId() {
        return commentId;
    }

    @DynamoDBRangeKey(attributeName = "UserId")
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute(attributeName = "UserName")
    public String getUserName(){
        return userName;
    }
    @DynamoDBAttribute(attributeName = "Contents")
    public String getContents(){
        return contents;
    }

    @DynamoDBAttribute(attributeName = "EpisodeId")
    public String getEpisodeId(){
        return episodeId;
    }

    @DynamoDBAttribute(attributeName = "Timestamp")
    public long getTimestamp(){
        return timestamp;
    }

    @DynamoDBAttribute(attributeName = "Likes")
    public int getLikes() {
        return likes;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setEpisodeId(String episodeId) {
        this.episodeId = episodeId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void addLike() {
        this.likes += 1;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRecord that = (CommentRecord) o;
        return Objects.equals(commentId, that.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }
}

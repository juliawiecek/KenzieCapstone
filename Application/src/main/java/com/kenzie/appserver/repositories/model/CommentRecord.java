package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "Comment")
public class CommentRecord {
    private String commentId;
    private String userId;
    private String userName;
    private String contents;
    private String episodeId;
    private String timestamp;

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
    public String getTimestamp(){
        return timestamp;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

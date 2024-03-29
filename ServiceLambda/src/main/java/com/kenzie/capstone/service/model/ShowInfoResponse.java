package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShowInfoResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("genres")
    private List<String> genres;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("image")
    private List<String> image;
    @JsonProperty("summary")
    private String summary;

    public ShowInfoResponse(String name, List<String> genres, Double rating, List<String> image, String summary) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.image = image;
        this.summary = summary;
    }

    public ShowInfoData getShowInfoData() {
        // Return the show info data
        return new ShowInfoData(name, genres, rating, image, summary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }
}

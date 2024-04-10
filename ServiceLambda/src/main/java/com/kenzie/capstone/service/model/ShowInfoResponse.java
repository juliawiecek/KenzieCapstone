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
    private Rating rating;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("id")
    private String id;

    public ShowInfoResponse(String name, List<String> genres, Rating rating, Image image, String summary, String id) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.image = image;
        this.summary = summary;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Image getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Rating {
        @JsonProperty("average")
        private Double average;

        public Rating() {
        }

        public Rating(Double average) {
            this.average = average;
        }


        public Double getAverage() {
            return average;
        }

        public void setAverage(Double average) {
            this.average = average;
        }
    }

    public static class Image {
        @JsonProperty("medium")
        private String medium;
        @JsonProperty("original")
        private String original;

        public Image() {

        }

        public Image(String medium, String original) {
            this.medium = medium;
            this.original = original;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }
    }
}

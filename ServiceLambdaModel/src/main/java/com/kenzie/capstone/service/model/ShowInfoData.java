package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ShowInfoData {
    private String name;
    private List<String> genres;
    private Double rating;
    private List<String> image;
    private String summary;

    public ShowInfoData(String name, List<String> genres, Double rating, List<String> image, String summary) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.image = image;
        this.summary = summary;
    }

    public ShowInfoData() {}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ShowInfoData that = (ShowInfoData) o;
        return Objects.equals(name, that.name) && Objects.equals(genres, that.genres) && Objects.equals(rating, that.rating)
                && Objects.equals(image, that.image) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genres, rating, image, summary);
    }
}

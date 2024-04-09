package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ShowInfoData {
    private String name;
    private List<String> genres;
    private Double rating;
    private String mediumImage;
    private String originalImage;
    private String summary;
    private int id;

    public ShowInfoData(String name, List<String> genres, Double rating, String mediumImage, String originalImage, String summary, int id) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.mediumImage = mediumImage;
        this.originalImage = originalImage;
        this.summary = summary;
        this.id = id;
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

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                && Objects.equals(mediumImage, that.mediumImage) && Objects.equals(originalImage, that.originalImage) && Objects.equals(summary, that.summary) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genres, rating, mediumImage, originalImage, summary, id);
    }
}

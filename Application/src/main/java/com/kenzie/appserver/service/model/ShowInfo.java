package com.kenzie.appserver.service.model;

import com.kenzie.capstone.service.model.ShowInfoData;
import com.kenzie.capstone.service.model.ShowInfoResponse;

import java.util.List;

public class ShowInfo {
    private String name;
    private List<String> genres;
    private Double rating;
    private String mediumImage;
    private String originalImage;
    private String summary;
    private int id;

    public ShowInfo(String name, List<String> genres, Double rating, String mediumImage, String originalImage, String summary, int id) {
        this.name = name;
        this.genres = genres;
        this.rating = rating;
        this.mediumImage = mediumImage;
        this.originalImage = originalImage;
        this.summary = summary;
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Double getRating() {
        return rating;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public String getSummary() {
        return summary;
    }

    public int getId() {
        return id;
    }
}
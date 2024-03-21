package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EpisodeData {
    private String name;
    private int episodeOrder;
    private int number;
    private int runtime;
    private String image;
    private String summary;

    public EpisodeData(String name, int episodeOrder, int number, int runtime, String image, String summary) {
        this.name = name;
        this.episodeOrder = episodeOrder;
        this.number = number;
        this.runtime = runtime;
        this.image = image;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisodeOrder() {
        return episodeOrder;
    }

    public void setEpisodeOrder(int episodeOrder) {
        this.episodeOrder = episodeOrder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

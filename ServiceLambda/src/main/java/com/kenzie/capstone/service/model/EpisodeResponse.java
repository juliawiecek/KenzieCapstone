package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpisodeResponse {
    @JsonProperty("name")
    private String name;
    @JsonProperty("episodeOrder")
    private int episodeOrder;
    @JsonProperty("number")
    private int number;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("image")
    private String image;
    @JsonProperty("summary")
    private String summary;

    public EpisodeResponse(String name, int episodeOrder, int number, int runtime, String image, String summary) {
        this.name = name;
        this.episodeOrder = episodeOrder;
        this.number = number;
        this.runtime = runtime;
        this.image = image;
        this.summary = summary;
    }

    public EpisodeData getEpisodeData() {
        return new EpisodeData(name, episodeOrder, number, runtime, image, summary);
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

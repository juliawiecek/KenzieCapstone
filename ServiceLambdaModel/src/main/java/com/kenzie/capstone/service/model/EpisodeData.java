package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EpisodeData {
    @JsonProperty("name")
    private String name;
    @JsonProperty("season")
    private int season;
    @JsonProperty("number")
    private int number;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("summary")
    private String summary;

    public EpisodeData() {
    }

    public EpisodeData(String name, int season, int number, int runtime, Image image, String summary) {
        this.name = name;
        this.season = season;
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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public static class Image {
        @JsonProperty("medium")
        private String medium;
        @JsonProperty("original")
        private String original;

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

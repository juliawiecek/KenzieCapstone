package com.kenzie.appserver.service.model;

public class Episode {
    private String name;
    private int episodeOrder;
    private int number;
    private int runtime;
    private String image;
    private String summary;

    public Episode(String name, int episodeOrder, int number, int runtime, String image, String summary) {
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

    public int getEpisodeOrder() {
        return episodeOrder;
    }

    public int getNumber() {
        return number;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getImage() {
        return image;
    }

    public String getSummary() {
        return summary;
    }
}

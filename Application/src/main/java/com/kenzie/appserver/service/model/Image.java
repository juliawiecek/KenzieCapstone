package com.kenzie.appserver.service.model;

import java.util.List;

public class Image {
    private String id;
    private String type;
    private boolean main;
    private List<String> resolutions;

    public Image(String id, String type, boolean main, List<String> resolutions) {
        this.id = id;
        this.type = type;
        this.main = main;
        this.resolutions = resolutions;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public boolean isMain() {
        return main;
    }

    public List<String> getResolutions() {
        return resolutions;
    }
}

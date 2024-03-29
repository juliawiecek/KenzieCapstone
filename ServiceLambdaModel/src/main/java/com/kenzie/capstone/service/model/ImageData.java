package com.kenzie.capstone.service.model;

import java.util.List;
public class ImageData {
    private String id;
    private String type;
    private boolean main;
    private List<String> resolutions;

    public ImageData(String id, String type, boolean main, List<String> resolutions) {
        this.id = id;
        this.type = type;
        this.main = main;
        this.resolutions = resolutions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public List<String> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<String> resolutions) {
        this.resolutions = resolutions;
    }
}


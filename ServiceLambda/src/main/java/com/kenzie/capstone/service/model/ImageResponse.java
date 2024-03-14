package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class ImageResponse {
    @JsonProperty("id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("main")
    private boolean main;
    // Ask Julia about this
    @JsonProperty("resolutions")
    private String resolutions;

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

    public String getResolutions() {
        return resolutions;
    }

    public void setResolutions(String resolutions) {
        this.resolutions = resolutions;
    }
}

package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageResponse {
    @JsonProperty("type")
    private String type;
    @JsonProperty("resolutions")
    private Resolutions resolutions;

    public ImageResponse(String type, Resolutions resolutions) {
        this.type = type;
        this.resolutions = resolutions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Resolutions getResolutions() {
        return resolutions;
    }

    public void setResolutions(Resolutions resolutions) {
        this.resolutions = resolutions;
    }

    public static class Resolutions {
        @JsonProperty("original")
        private Original original;

        public Original getOriginal() {
            return original;
        }

        public void setOriginal(Original original) {
            this.original = original;
        }

        public static class Original {
            @JsonProperty("url")
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

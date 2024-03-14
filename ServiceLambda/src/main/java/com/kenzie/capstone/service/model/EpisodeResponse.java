package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude
public class EpisodeResponse {
    @JsonProperty("name")
    private String name;
//    @JsonProperty("episodeOrder")
//    private int episodeOrder;
    @JsonProperty("number")
    private int number;
    @JsonProperty("runtime")
    private int runtime;
    @JsonProperty("image")
    private String image;
    @JsonProperty("summary")
    private String summary;
}

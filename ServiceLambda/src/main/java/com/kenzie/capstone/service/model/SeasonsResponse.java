package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeasonsResponse {
    @JsonProperty("number")
    private int number;
    @JsonProperty("id")
    private int id;

    public SeasonsResponse(int number, int id) {
        this.number = number;
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

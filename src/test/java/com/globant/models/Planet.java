package com.globant.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Planet {

    @JsonProperty("name")
    private String name;

    @JsonProperty("rotation_period")
    private String rotationPeriod;

    @JsonProperty("orbital_period")
    private String orbitalPeriod;

    @JsonProperty("diameter")
    private String diameter;

    @JsonProperty("climate")
    private String climate;

    @JsonProperty("gravity")
    private String gravity;

    @JsonProperty("terrain")
    private String terrain;

    @JsonProperty("surface_water")
    private String surfaceWater;

    @JsonProperty("population")
    private String population;
    @JsonProperty("residents")
    private List<String> residents;
    @JsonProperty("films")
    private List<String> films;
    @JsonProperty("created")
    private String created;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("url")
    private String url;

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

}


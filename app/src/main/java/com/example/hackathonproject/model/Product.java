package com.example.hackathonproject.model;

import java.util.HashMap;

public class Product {

    // CONSTANTS

    // FIELDS
    private int id;
    private HashMap<String, Integer> scores;
    private String name;
    private String description;
    private int aisle;

    // METHODS
    public Product(int id, String name, String description, int aisle) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.scores = new HashMap<>();
        this.aisle = aisle;
    }

    public Integer getScore(String attribute) {
        return this.scores.get(attribute);
    }

    public void addAttribute(String attribute, int score) {
        this.scores.put(attribute, score);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAisle() {
        return aisle;
    }

}

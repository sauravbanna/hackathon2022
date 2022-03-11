package com.example.hackathonproject.model;

import com.example.hackathonproject.model.scores.Score;

import java.util.HashMap;

public class Product {

    // CONSTANTS

    // FIELDS
    private int id;
    private HashMap<String, Score> scores;
    private String name;
    private String description;


    // METHODS
    public Product(String name, String description) {
        this.name = name;
        this.description = description;
        this.scores = new HashMap<>();
    }

    public Score getScore(String attribute) {
        return this.scores.get(attribute);
    }

    public void addAttribute(String attribute, Score score) {
        this.scores.put(attribute, score);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}

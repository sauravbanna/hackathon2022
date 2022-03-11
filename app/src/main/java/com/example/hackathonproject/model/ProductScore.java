package com.example.hackathonproject.model;

import java.util.List;

public class ProductScore {

    // CONSTANTS

    // FIELDS
    private int product;
    private List<Integer> scores;

    // METHODS
    public ProductScore(Integer product, List<Integer> scores) {
        this.product = product;
        this.scores = scores;
    }
}

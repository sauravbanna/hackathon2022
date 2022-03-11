package com.example.hackathonproject.model;

import java.util.List;

public class ProductScore {

    // CONSTANTS

    // FIELDS
    private Product product;
    private int score;

    // METHODS
    public ProductScore(Product product, int score) {
        this.product = product;
        this.score = score;
    }

    public void updateProductScore() {
        this.score = calculateAverage();
    }

    private int calculateAverage() {
        double sumOfWeights = 0;
        for (Integer s : Store.currentAttributes.values()) {
            sumOfWeights += s;
        }
        
        double sumOfWeightAndValue = 0;
        for (String s : Store.currentAttributes.keySet()) {
            sumOfWeightAndValue += (Store.currentAttributes.get(s)
                    * product.getScore(s));
        }

        return (int)((sumOfWeightAndValue) / (sumOfWeights));
    }

    public Product getProduct() {
        return product;
    }

    public String toString() {
        return product.getName();
    }

    public int getScore() {
        return this.score;
    }
}

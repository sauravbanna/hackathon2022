package com.example.hackathonproject.model;

import com.example.hackathonproject.model.scores.Score;

import java.util.List;

public class ProductScore {

    // CONSTANTS

    // FIELDS
    private Product product;
    private List<Integer> scores;

    // METHODS
    public ProductScore(Product product, List<Integer> scores) {
        this.product = product;
        this.scores = scores;
    }

    public void updateProductScore(Score score, int position, String attribute) {
        if (position < 1) {
            scores.add(0, calculateAverage(getScore(score), attribute));
        } else {
            scores.add(position,calculateAverage(getScore(score), attribute));
        }
    }

    private int getScore(Score score) {
        switch (score.getClass().getName()) {
            case "ColourScore" :
                int colourScore = (int)(10 * (Math.abs(Integer.parseInt(score.getColour(), 16)
                        - (Integer.parseInt(product.getScore("colour").getColour()))) /
                        (Integer.parseInt(product.getScore("colour").getColour()))));
                return colourScore;
            case "PriceScore" :
                int priceScore = (int)(10 * Math.abs((score.getPrice()) -
                        ((product.getScore("price").getPrice())))/
                        (product.getScore("price").getPrice()));
                return priceScore;
            default :
                int ratingsScore = score.getRating();
                return ratingsScore;
        }
    }

    private int calculateAverage(int score, String attribute) {
        int sumOfWeights = 0;
        for (Score s : Store.currentAttributes.values()) {
            sumOfWeights += getScore(s);
        }
        
        int sumOfWeightAndValue = 0;
        for (String s : Store.currentAttributes.keySet()) {
            sumOfWeightAndValue += (getScore(Store.currentAttributes.get(s))
                    * getScore(product.getScore(s)));
        }

        return (sumOfWeightAndValue + score*getScore(product.getScore(attribute)))
                / (sumOfWeights + score);
    }

    public int getScoreAtPosition(int position) {
        return scores.get(position);
    }

    public String toString() {
        return product.getName();
    }
}

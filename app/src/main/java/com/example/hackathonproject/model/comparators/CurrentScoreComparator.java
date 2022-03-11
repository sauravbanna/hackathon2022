package com.example.hackathonproject.model.comparators;

import com.example.hackathonproject.model.ProductScore;

import java.util.Comparator;

public class CurrentScoreComparator implements Comparator<ProductScore> {

    private int currentScorePosition;

    public CurrentScoreComparator(int currentScorePosition) {
        this.currentScorePosition = currentScorePosition;
    }

    @Override
    public int compare(ProductScore p1, ProductScore p2) {
        int val1 = p1.getScoreAtPosition(currentScorePosition);
        int val2 = p2.getScoreAtPosition(currentScorePosition);

        if (val1 == val2) {
            return 0;
        } else if (val1 > val2) {
            return 1;
        } else {
            return -1;
        }
    }
}

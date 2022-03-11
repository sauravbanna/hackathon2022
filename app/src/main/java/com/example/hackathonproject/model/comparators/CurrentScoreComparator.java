package com.example.hackathonproject.model.comparators;

import com.example.hackathonproject.model.ProductScore;

import java.util.Comparator;

public class CurrentScoreComparator implements Comparator<ProductScore> {

    @Override
    public int compare(ProductScore p1, ProductScore p2) {
        int val1 = p1.getScore();
        int val2 = p2.getScore();

        System.out.println(val1);
        System.out.println(val2);

        return val1 - val2;
    }
}

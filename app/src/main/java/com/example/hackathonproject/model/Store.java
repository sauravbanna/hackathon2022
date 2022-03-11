package com.example.hackathonproject.model;

import com.example.hackathonproject.model.comparators.CurrentScoreComparator;
import com.example.hackathonproject.model.scores.Score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Store {

    // CONSTANTS
    public static final int MAX_ID = 100000;
    public static HashMap<Integer, Product> PRODUCTS;

    public static final List<Question> QUESTIONS = Arrays.asList(new Question[]
            {new Question("What is your maximum budget?", 2, "price"),
                    new Question("How important is battery life to you?", 0, "battery"),
                    new Question("How important is processing power to you?", 0, "battery"),
                    new Question("How important is screen resolution to you?", 0, "battery"),
                    new Question("Would you like a dedicated GPU in your machine?", 1, "battery"),
                    new Question("How important is RAM amount for you?", 0, "battery"),
                    new Question("How important is Hard Drive capacity for you?", 0, "battery"),
                    new Question("What colour would you like your laptop?", 3, "battery")
            });
    public static List<String> ATTRIBUTES = Arrays.asList(new String[]
            {"price", "battery", "processing power", "resolution", "gpu", "ram", "hard drive", "color"});

    public static final int MAX_PRICE = 4000;
    public static final int MAX_BATTERY = 24;
    public static final double MAX_PROCESSING_POWER = 5.0;
    public static final int MAX_RESOLUTION = 7680 * 4320;
    public static final int MAX_GPU = 3000;
    public static final int MAX_RAM = 32;
    public static final int MAX_HARD_DRIVE = 2;

    // FIELDS
    private List<ProductScore> productScores;
    private int currentScorePosition;
    public static HashMap<String, Score> currentAttributes;
    private int maxPrice;

    // METHODS

    public Store() {
        this.currentScorePosition = 0;
        this.PRODUCTS = new HashMap<>();
        this.productScores = new ArrayList<>();
        this.currentAttributes = new HashMap<>();
        this.maxPrice = 0;
    }

    public void setProducts(HashMap<Integer, Product> products) {
        this.PRODUCTS = products;
        for (Product p : this.PRODUCTS.values()) {
            this.productScores.add(new ProductScore(p, new ArrayList<>()));
        }
    }

    public void sort(String attribute) {
        updateProductScores(attribute);

        Collections.sort(productScores, new CurrentScoreComparator(currentScorePosition));
    }

    private void updateProductScores(String attribute) {
        for (ProductScore ps : productScores) {
            ps.updateProductScore(currentAttributes.get(attribute), currentScorePosition, attribute);
        }
    }


    public void addAttribute(String attribute, Score score) {
        currentAttributes.put(attribute, score);
        sort(attribute);

    }

    public void addProduct(Product product) {
        int randomId = (int) (Math.random() * MAX_ID);

        while (PRODUCTS.containsKey(randomId)) {
            randomId = (int) (Math.random() * MAX_ID);
        }

        PRODUCTS.put(randomId, product);
    }

    public int getCurrentScorePosition() {
        return currentScorePosition;
    }

    public void addCurrentScorePosition() {
        currentScorePosition++;
    }

    public boolean scorePositionCheck() {
        return !(currentScorePosition >= QUESTIONS.size());
    }

    public List<Question> getQuestions() {
        return QUESTIONS;
    }

    public List<ProductScore> getProductScores() {
        return productScores;
    }


}

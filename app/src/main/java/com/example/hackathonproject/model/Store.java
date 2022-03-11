package com.example.hackathonproject.model;

import com.example.hackathonproject.model.comparators.CurrentScoreComparator;

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
            {new Question("What is your ideal budget?", 2, "price"),
                    new Question("How important is battery life to you?", 0, "battery"),
                    new Question("How important is processing power to you?", 0, "processing power"),
                    new Question("How important is screen resolution to you?", 0, "resolution"),
                    new Question("Would you like a dedicated GPU?", 1, "gpu"),
                    new Question("How important is RAM amount for you?", 0, "ram"),
                    new Question("How important is Hard Drive capacity for you?", 0, "hard drive"),
            });
    public static List<String> ATTRIBUTES = Arrays.asList(new String[]
            {"price", "battery", "processing power", "resolution", "gpu", "ram", "hard drive"});

    public static final int MAX_PRICE = 5000;

    // FIELDS
    private List<ProductScore> productScores;
    private int currentScorePosition;
    public static HashMap<String, Integer> currentAttributes;
    private int maxPrice;

    // METHODS

    public Store() {
        this.currentScorePosition = 0;
        this.PRODUCTS = new HashMap<>();
        this.productScores = new ArrayList<>();
        this.currentAttributes = new HashMap<>();
        this.maxPrice = 0;
    }

    public void initProductScores() {
        for (Product p : PRODUCTS.values()) {
            productScores.add(new ProductScore(p, 0));
        }
    }

    public void setProducts(HashMap<Integer, Product> products) {
        this.PRODUCTS = products;
        for (Product p : this.PRODUCTS.values()) {
            this.productScores.add(new ProductScore(p, 0));
        }
    }

    public void sort() {
        updateProductScores();


        Collections.sort(productScores, new CurrentScoreComparator());
        Collections.reverse(productScores);
    }

    private void updateProductScores() {
        for (ProductScore ps : productScores) {

            ps.updateProductScore();
        }
    }


    public void addAttribute(String attribute, Integer score) {
        currentAttributes.put(attribute, score);
        sort();

    }

    public void removeAttribute(String attribute) {
        if (currentAttributes.containsKey(attribute)) {
            currentAttributes.remove(attribute);
            sort();
        }
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

    public void reduceCurrectScorePosition() {
        currentScorePosition--;
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

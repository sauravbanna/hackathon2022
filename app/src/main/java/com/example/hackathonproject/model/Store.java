package com.example.hackathonproject.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Store {

    // CONSTANTS

    // FIELDS
    private List<ProductScore> productScores;
    private HashMap<Integer, Product> products;
    private int currentScorePosition;
    private HashMap<String, Integer> currentAttributes;

    // METHODS

    public Store() {
        this.currentScorePosition = 0;
        this.products = new HashMap<>();
        this.productScores = new ArrayList<>();
        this.currentAttributes = new HashMap<>();
    }

    public void setProducts(HashMap<Integer, Product> products) {
        this.products = products;
        for (Integer i : this.products.keySet()) {
            this.productScores.add(new ProductScore(i, new ArrayList<>()));
        }
    }

    public void sort(String attribute) {
        updateProductScores(attribute);


    }

    private void updateProductScores(String attribute) {
        for (ProductScore ps : productScores) {
            updateProductScore(attribute);
        }
    }

    private void updateProductScore(String attribute) {

    }

    public void addAttribute(String attribute, int score) {

    }

    public void addProduct(Product product) {

    }







}

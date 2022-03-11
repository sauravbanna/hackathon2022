package com.example.hackathonproject.model.scores;

public abstract class Score {

    private String colour;
    private Integer rating;
    private Integer price;

    public Score(String colour, Integer rating, Integer price) {
        this.colour = colour;
        this.rating = rating;
        this.price = price;
    }

    public String getColour() {
        return colour;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getPrice() {
        return price;
    }

}

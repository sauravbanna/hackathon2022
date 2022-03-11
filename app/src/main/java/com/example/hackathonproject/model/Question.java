package com.example.hackathonproject.model;

public class Question {

    // FIELDS

    private String question;
    private String attribute;
    // 3 types
    // 0 is Rating Slider Question from 1-10
    // 1 is Yes / No / Doesn't Matter Question
    // 2 is Price Slider Question
    // 3 is Colour question
    private int type;

    // METHODS

    public Question(String question, int type, String attribute) {
        this.question = question;
        this.type = type;
        this.attribute = attribute;
    }

    public String getQuestion() {
        return question;
    }

    public int getType() {
        return type;
    }

    public String getAttribute() {
        return attribute;
    }


}

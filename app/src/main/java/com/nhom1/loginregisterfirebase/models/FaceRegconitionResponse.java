package com.nhom1.loginregisterfirebase.models;

public class FaceRegconitionResponse {
    private String message;
    private String score;

    public FaceRegconitionResponse(String message, String score) {
        this.message = message;
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

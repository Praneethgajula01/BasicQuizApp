package com.example.quiz;

public class Question {
    private String questionText;
    private String correctAnswer;

    // Constructor
    public Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    // Getters (no setters needed as questions are immutable once loaded)
    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // Optional: Override toString() for easier debugging
    @Override
    public String toString() {
        return "Question: " + questionText + ", Answer: " + correctAnswer;
    }
}
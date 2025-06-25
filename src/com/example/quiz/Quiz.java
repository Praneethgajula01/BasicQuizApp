package com.example.quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections; // For shuffling
import java.util.List;
import java.util.Scanner;

public class Quiz {
    private List<Question> questions;
    private int score;
    private Scanner scanner;

    public Quiz(String filePath) {
        this.questions = new ArrayList<>();
        this.score = 0;
        this.scanner = new Scanner(System.in);
        loadQuestionsFromFile(filePath);
    }

    private void loadQuestionsFromFile(String filePath) {
        // Using try-with-resources to ensure BufferedReader is closed
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming CSV format: "Question Text,Correct Answer"
                String[] parts = line.split(","); // Split by comma
                if (parts.length == 2) { // Ensure line has both question and answer
                    questions.add(new Question(parts[0].trim(), parts[1].trim()));
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
            System.out.println("Loaded " + questions.size() + " questions.");
            Collections.shuffle(questions); // Shuffle questions for variety
        } catch (IOException e) {
            System.err.println("Error loading questions from file: " + e.getMessage());
            // Handle the error (e.g., exit, or proceed with empty quiz)
            // For a basic app, we might just print and continue
        }
    }

    public void startQuiz() {
        if (questions.isEmpty()) {
            System.out.println("No questions available to start the quiz. Exiting.");
            return;
        }

        System.out.println("\n--- Welcome to the Java Quiz! ---");
        System.out.println("Answer the following questions. Type your answer and press Enter.");

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.getQuestionText());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();

            if (userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer().trim())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer was: " + currentQuestion.getCorrectAnswer());
            }
        }

        endQuiz();
    }

    private void endQuiz() {
        System.out.println("\n--- Quiz Finished! ---");
        System.out.println("You answered " + score + " out of " + questions.size() + " questions correctly.");
        System.out.println("Your final score: " + score + "/" + questions.size());
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}
package com.example.quiz;

import java.io.File; // Import File class

public class Main {
    public static void main(String[] args) {
        // Option 1: Direct relative path (most reliable for simple projects in IDEs)
        // Assumes 'resources' folder is in the project root.
        String filePath = "resources" + File.separator + "questions.csv";

        // Option 2: Using getResource() - This is generally better for packaged JARs,
        // but can be tricky with IDE specific run configurations.
        // Keeping it commented out for now as Option 1 is simpler for your current issue.
        /*
        String resourceName = "questions.csv";
        URL resourceUrl = Main.class.getClassLoader().getResource(resourceName);
        if (resourceUrl == null) {
            System.err.println("Error: Resource '" + resourceName + "' not found via ClassLoader.");
            return;
        }
        try {
            filePath = java.net.URLDecoder.decode(resourceUrl.getPath(), "UTF-8");
            if (System.getProperty("os.name").toLowerCase().contains("windows") && filePath.startsWith("/")) {
                filePath = filePath.substring(1);
            }
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("Error decoding file path: " + e.getMessage());
            return;
        }
        */

        System.out.println("Attempting to load questions from: " + filePath); // Add this for debugging

        Quiz quiz = new Quiz(filePath);
        quiz.startQuiz();
    }
}
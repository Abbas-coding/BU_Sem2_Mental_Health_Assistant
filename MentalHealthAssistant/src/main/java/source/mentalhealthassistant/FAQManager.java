package source.mentalhealthassistant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FAQManager {
    private Map<String, String> faqMap;

    public FAQManager(String filePath) {
        faqMap = new HashMap<>();
        loadFAQs(filePath);
    }

    // Load FAQs from the text file into the map
    private void loadFAQs(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Skip empty lines or lines without the delimiter
                if (line.trim().isEmpty() || !line.contains(":::")) continue;

                String[] parts = line.split(":::", 2); // Split into question and answer
                if (parts.length == 2) {
                    String question = parts[0].trim().toLowerCase();
                    String answer = parts[1].trim();
                    faqMap.put(question, answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception as per your application's requirement
        }
    }

    // Retrieve an answer based on the user question
    public String getAnswer(String question) {
        if (question == null) return null;
        return faqMap.get(question.toLowerCase());
    }
}



package source.mentalhealthassistant.core;

import com.google.gson.Gson;
import source.mentalhealthassistant.FAQManager;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ChatBot {
    private String botName;
    private Map<String, String> responses; // Optional for static responses
    private final HttpClient httpClient;
    private final Gson gson;
    private static final String RASA_URL = "http://0.0.0.0:5005/webhooks/rest/webhook"; // Rasa server endpoint
    private FAQManager faqManager;

    public ChatBot(String botName, String faqFilePath) {
        this.botName = botName;
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.faqManager = new FAQManager(faqFilePath); // Initialize FAQManager
    }

    // Send user input to Rasa or retrieve from FAQs and get a response
    public String getResponse(String userInput) {
        // First, check if the userInput matches any FAQ
        String faqAnswer = faqManager.getAnswer(userInput);
        if (faqAnswer != null) {
            return faqAnswer;
        }

        // If not found in FAQs, proceed to query the Rasa chatbot
        try {
            // Create JSON payload
            String payload = gson.toJson(new RasaMessage("user", userInput));

            // Build HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(RASA_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            // Send request and receive response
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse response JSON
            RasaResponse[] rasaResponses = gson.fromJson(response.body(), RasaResponse[].class);
            if (rasaResponses.length > 0) {
                return rasaResponses[0].getText(); // Return the first response text
            } else {
                return "No response from chatbot.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error communicating with chatbot.";
        }
    }

    public String getBotName() {
        return botName;
    }

    // Inner class for Rasa request structure
    private static class RasaMessage {
        private String sender;
        private String message;

        public RasaMessage(String sender, String message) {
            this.sender = sender;
            this.message = message;
        }
    }

    // Inner class for Rasa response structure
    private static class RasaResponse {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}


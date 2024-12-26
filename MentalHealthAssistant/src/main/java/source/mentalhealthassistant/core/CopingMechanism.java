////package source.mentalhealthassistant.core;
////
////public abstract class CopingMechanism {
////    private String name;
////    private String description;
////
////    public CopingMechanism(String name, String description) {
////        this.name = name;
////        this.description = description;
////    }
////
////    public abstract void performActivity();
////        // To be implemented by subclasses
////
////    public String getName() {
////        return name;
////    }
////
////    public String getDescription() {
////        return description;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public void setDescription(String description) {
////        this.description = description;
////    }
////
////
////}
//package source.mentalhealthassistant.core;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Random;
//
//public class CopingMechanism {
//    private final List<String> depressedActivities = new ArrayList<>(Arrays.asList(
//            "Try journaling your feelings.",
//            "Take a walk in nature.",
//            "Listen to soothing music or watch a comforting movie.",
//            "Consider reaching out to a friend or loved one."
//    ));
//
//    private final List<String> anxiousActivities = new ArrayList<>(Arrays.asList(
//            "Practice deep breathing or mindfulness meditation.",
//            "Engage in light physical activity, like stretching or yoga.",
//            "Write down your thoughts to process them."
//    ));
//
//    private final List<String> happyActivities = new ArrayList<>(Arrays.asList(
//            "Share your joy with someone you care about.",
//            "Engage in your favorite hobby or activity.",
//            "Try something creative, like painting, cooking, or playing music."
//    ));
//
//    private final List<String> generalActivities = new ArrayList<>(Arrays.asList(
//            "Exercise: Go for a walk, do yoga, or try dancing.",
//            "Relax: Listen to calming music or watch a favorite show.",
//            "Reflect: Write in a journal or meditate for 10 minutes."
//    ));
//
//    private List<String> availableActivities; // List of available activities to suggest
//    private List<String> performedActivities; // List of performed activities to avoid repetition
//    private Random random = new Random();
//
//    public CopingMechanism() {
//        // Initialize available activities as empty
//        availableActivities = new ArrayList<>();
//        performedActivities = new ArrayList<>();
//    }
//
//    public CopingMechanism(String name, String description) {
//    }
//
//    // Suggest a single activity based on mood
//    public void suggestActivityBasedOnMood(String mood) {
//        availableActivities.clear(); // Reset available activities list
//
//        // Load activities based on mood
//        switch (mood.toLowerCase()) {
//            case "depressed":
//            case "sad":
//                availableActivities.addAll(depressedActivities);
//                break;
//            case "anxious":
//                availableActivities.addAll(anxiousActivities);
//                break;
//            case "happy":
//            case "content":
//                availableActivities.addAll(happyActivities);
//                break;
//            default:
//                availableActivities.addAll(generalActivities);
//        }
//
//        suggestNewActivity();
//    }
//
//    // Suggest a new activity from the available list
//    public void suggestNewActivity() {
//        if (!availableActivities.isEmpty()) {
//            // Select a random activity
//            String activity = availableActivities.get(random.nextInt(availableActivities.size()));
//            System.out.println("Here's a suggestion for you: " + activity);
//
//            // Remove the suggested activity from the list to avoid repetition
//            availableActivities.remove(activity);
//        } else {
//            System.out.println("You've gone through all available suggestions. Would you like to restart?");
//        }
//    }
//
//    // Perform a specific activity based on user's choice
//    public void performActivity(int choice) {
//        switch (choice) {
//            case 1:
//                performMindfulnessExercise();
//                break;
//            case 2:
//                performPhysicalActivity();
//                break;
//            case 3:
//                performJournaling();
//                break;
//            default:
//                System.out.println("Invalid choice. Please select a valid option.");
//        }
//    }
//
//    private void performMindfulnessExercise() {
//        if (performedActivities.contains("Mindfulness Exercise")) {
//            System.out.println("You've already performed this activity. Here's another one:");
//            suggestNewActivity(); // Suggest a different activity
//        } else {
//            System.out.println("\n--- Mindfulness Exercise ---");
//            System.out.println("Focus on your breathing for 5 minutes:");
//            System.out.println("1. Inhale deeply through your nose for 4 seconds.");
//            System.out.println("2. Hold your breath for 4 seconds.");
//            System.out.println("3. Exhale slowly through your mouth for 6 seconds.");
//            System.out.println("Repeat the cycle for 5 minutes.");
//
//            performedActivities.add("Mindfulness Exercise"); // Add to performed list
//        }
//    }
//
//    private void performPhysicalActivity() {
//        if (performedActivities.contains("Physical Activity")) {
//            System.out.println("You've already performed this activity. Here's another one:");
//            suggestNewActivity(); // Suggest a different activity
//        } else {
//            System.out.println("\n--- Physical Activity ---");
//            System.out.println("Go for a 15-minute walk or try a simple workout at home.");
//            System.out.println("Enjoy the fresh air and let your mind relax.");
//
//            performedActivities.add("Physical Activity"); // Add to performed list
//        }
//    }
//
//    private void performJournaling() {
//        if (performedActivities.contains("Journaling")) {
//            System.out.println("You've already performed this activity. Here's another one:");
//            suggestNewActivity(); // Suggest a different activity
//        } else {
//            System.out.println("\n--- Journaling ---");
//            System.out.println("Take a few minutes to reflect on your thoughts.");
//            System.out.println("Prompt: 'What are three things you're grateful for today?'");
//            System.out.println("Write freely and let your thoughts flow.");
//
//            performedActivities.add("Journaling"); // Add to performed list
//        }
//    }
//
//    // Reset performed activities
//    public void resetPerformedActivities() {
//        performedActivities.clear();
//        System.out.println("All activities have been reset. You can start performing activities again.");
//    }
//}

package source.mentalhealthassistant.core;

public class CopingMechanism {

    public void displayCopingMechanisms() {
        // Display the importance of mental health and different coping mechanisms
        System.out.println("Mental health is just as important as physical health. Taking care of your mental well-being is essential for living a fulfilling life.");
        System.out.println("Here are some coping mechanisms that can help you manage stress and emotions:");

        // Display mindfulness activities
        System.out.println("\nMindfulness Activities:");
        System.out.println("- Mindful breathing: Inhale for 4 seconds, hold for 4 seconds, exhale for 6 seconds.");
        System.out.println("- Meditation: Focus on your breath or listen to a guided meditation.");
        System.out.println("- Grounding exercises: Focus on the present moment by observing your surroundings.");

        // Display physical activities
        System.out.println("\nPhysical Activities:");
        System.out.println("- Go for a walk in nature.");
        System.out.println("- Try some yoga or stretching exercises.");
        System.out.println("- Do a simple home workout to release tension and boost mood.");

        // Display journaling ideas
        System.out.println("\nJournaling:");
        System.out.println("- Write about your day and how you're feeling.");
        System.out.println("- Create a gratitude list: Write down 3 things you're grateful for.");
        System.out.println("- Reflect on your thoughts and emotions in a journal entry.");
    }
}

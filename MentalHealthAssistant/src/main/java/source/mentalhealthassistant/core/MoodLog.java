//package source.mentalhealthassistant.core;
////
////import java.time.LocalDateTime;
////
////public class MoodLog {
////    private String moodType;
////    private int rating;
////    private String description;
////    private LocalDateTime timestamp;
////
////    public MoodLog(String moodType, int rating, String description, LocalDateTime timestamp) {
////        this.moodType = moodType;
////        this.rating = rating;
////        this.description = description;
////        this.timestamp = timestamp;
////    }
////    public String getMoodType() {
////        return moodType;
////    }
////    public int getRating() {
////        return rating;
////    }
////    public String getDescription() {
////        return description;
////    }
////    public LocalDateTime getTimestamp() {
////        return timestamp;
////    }
////    public void setMoodType(String moodType) {
////        this.moodType = moodType;
////    }
////    public void setRating(int rating) {
////        this.rating = rating;
////    }
////    public void setDescription(String description) {
////        this.description = description;
////    }
////    public void setTimestamp(LocalDateTime timestamp) {
////        this.timestamp = timestamp;
////    }
////}
//import java.sql.*;
//import java.time.LocalDate;
//import java.util.Scanner;
//
//public class MoodLog {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/mental_health_assistant";
//    private static final String DB_USER = "root"; // Replace with your MySQL username
//    private static final String DB_PASSWORD = "castaway110"; // Replace with your MySQL password
//
//    private String username; // User's name
//
//    public MoodLog(String username) {
//        this.username = username;
//    }
//
//    // Method to track mood
//    public void trackMood() {
//        Scanner scanner = new Scanner(System.in);
//
//        // Prompt the user for their mood
//        System.out.println("How are you feeling today? (e.g., happy, sad, angry, etc.)");
//        String mood = scanner.nextLine().toLowerCase();
//
//        // Validate the mood (you can add more moods to this validation)
//        String[] validMoods = {"happy", "sad", "angry", "excited", "depressed", "anxious", "neutral"};
//        boolean isValidMood = false;
//        for (String validMood : validMoods) {
//            if (mood.equals(validMood)) {
//                isValidMood = true;
//                break;
//            }
//        }
//
//        while (!isValidMood) {
//            System.out.println("Invalid mood! Please choose one of these: happy, sad, angry, excited, depressed, anxious, neutral.");
//            mood = scanner.nextLine().toLowerCase();
//            for (String validMood : validMoods) {
//                if (mood.equals(validMood)) {
//                    isValidMood = true;
//                    break;
//                }
//            }
//        }
//
//        // Ask the user for a rating
//        System.out.println("On a scale of 1 to 10, how much are you feeling " + mood + "?");
//        int rating = 0;
//        while (rating < 1 || rating > 10) {
//            try {
//                rating = Integer.parseInt(scanner.nextLine());
//                if (rating < 1 || rating > 10) {
//                    System.out.println("Please enter a number between 1 and 10.");
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input! Please enter a number between 1 and 10.");
//            }
//        }
//
//        // Save mood to the database
//        saveMoodToDatabase(mood, rating);
//
//        System.out.println("Mood saved! Thank you for sharing.");
//    }
//
//    // Method to save mood to the database
//    private void saveMoodToDatabase(String mood, int rating) {
//        String insertQuery = "INSERT INTO moodlog (username, mood, rating, date) VALUES (?, ?, ?, ?)";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, mood);
//            preparedStatement.setInt(3, rating);
//            preparedStatement.setDate(4, Date.valueOf(LocalDate.now()));
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Error saving mood to database: " + e.getMessage());
//        }
//    }
//
//    // Method to display mood history
//    public void displayMoodHistory() {
//        String selectQuery = "SELECT date, mood, rating FROM moodlog WHERE username = ? ORDER BY date DESC";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
//
//            preparedStatement.setString(1, username);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            System.out.println("Your mood history:");
//            if (!resultSet.isBeforeFirst()) {
//                System.out.println("No mood history found.");
//                return;
//            }
//
//            while (resultSet.next()) {
//                Date date = resultSet.getDate("date");
//                String mood = resultSet.getString("mood");
//                int rating = resultSet.getInt("rating");
//
//                System.out.println(date + " | Mood: " + mood + " | Rating: " + rating);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error retrieving mood history: " + e.getMessage());
//        }
//    }
//}
package source.mentalhealthassistant.core;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoodLog {
    private int moodId;
    private String username;
    private Date date;
    private String mood;
    private int rating;
    private String description;

//    private static final String DB_URL = "jdbc:mysql://localhost:3306/mental_health_assistant";
//    private static final String DB_USER = "root"; // Replace with your MySQL username
//    private static final String DB_PASSWORD = "castaway110"; // Replace with your MySQL password

    public MoodLog(int moodId, String username, Date date, String mood, int rating, String description) {
        this.moodId = moodId;
        this.username = username;
        this.date = date;
        this.mood = mood;
        this.rating = rating;
        this.description = description;
    }

    public MoodLog(String username){this.username  = username;}

    public void trackMood() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How are you feeling today? (e.g., happy, sad, angry, etc.)");
        String mood = scanner.nextLine().toLowerCase();

        // Validate the mood
        String[] validMoods = {"happy", "sad", "angry", "excited", "depressed", "anxious", "neutral"};
        while (!isValidMood(mood, validMoods)) {
            System.out.println("Invalid mood! Please choose one of these: happy, sad, angry, excited, depressed, anxious, neutral.");
            mood = scanner.nextLine().toLowerCase();
        }

        // Ask for a rating
        System.out.println("On a scale of 1 to 10, how much are you feeling " + mood + "?");
        int rating = getValidRating(scanner);

        // ask for description
        System.out.println("Please describe your mood:");
        String description = scanner.nextLine();
        if(description.isEmpty()){
            System.out.println("Description cannot be empty. Please try again.");
            return;
        }

        // Save to database
        saveMoodToDatabase(mood, rating, description);
        System.out.println("Mood saved! Thank you for sharing.");
    }

    private boolean isValidMood(String mood, String[] validMoods) {
        for (String validMood : validMoods) {
            if (mood.equals(validMood)) {
                return true;
            }
        }
        return false;
    }

    private int getValidRating(Scanner scanner) {
        int rating = 0;
        while (rating < 1 || rating > 10) {
            try {
                rating = Integer.parseInt(scanner.nextLine());
                if (rating < 1 || rating > 10) {
                    System.out.println("Please enter a number between 1 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 10.");
            }
        }
        return rating;
    }

    private void saveMoodToDatabase(String mood, int rating, String description) throws ClassNotFoundException {
        String insertQuery = "INSERT INTO moodlog (username, mood, rating, description, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, mood);
            preparedStatement.setInt(3, rating);
            preparedStatement.setString(4, description);
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();
            System.out.println("Mood saved successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving mood to database: " + e.getMessage());
        }
    }

    public void displayMoodHistory() throws ClassNotFoundException {
        String selectQuery = "SELECT moodId, date, mood, rating, description FROM moodlog WHERE username = ? ORDER BY date DESC";
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Your mood history:");
            if (!resultSet.isBeforeFirst()) {
                System.out.println("No mood history found.");
                return;
            }

            while (resultSet.next()) {
                int moodId = resultSet.getInt("moodId");
                Date date = resultSet.getDate("date");
                String mood = resultSet.getString("mood");
                int rating = resultSet.getInt("rating");
                String description = resultSet.getString("description");

                System.out.printf("ID: %d | Date: %s | Mood: %s | Rating: %d | Description: %s%n",
                        moodId, date, mood, rating, description);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving mood history: " + e.getMessage());
        }
    }

    public void updateMood(int moodId, String mood, int rating, String description) throws ClassNotFoundException {
        String updateQuery = "UPDATE moodlog SET mood = ?, rating = ?, description = ? WHERE moodId = ?";
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, mood);
            preparedStatement.setInt(2, rating);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, moodId);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Mood log updated successfully.");
            } else {
                System.out.println("No mood log found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating mood log: " + e.getMessage());
        }
    }

    public void deleteMood(int moodId) throws ClassNotFoundException {
        String deleteQuery = "DELETE FROM moodlog WHERE moodId = ?";
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, moodId);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Mood log deleted successfully.");
            } else {
                System.out.println("No mood log found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting mood log: " + e.getMessage());
        }
    }


    public List<MoodLog> getAllMoodLogs() throws ClassNotFoundException {
        String selectQuery = "SELECT moodId, username, date, mood, rating, description FROM moodlog";
        List<MoodLog> moodLogs = new ArrayList<>();
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int moodId = resultSet.getInt("moodId");
                String username = resultSet.getString("username");
                Date date = resultSet.getDate("date");
                String mood = resultSet.getString("mood");
                int rating = resultSet.getInt("rating");
                String description = resultSet.getString("description");

                MoodLog moodLog = new MoodLog(moodId, username, date, mood, rating, description);
                moodLogs.add(moodLog);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving mood logs: " + e.getMessage());
        }
        return moodLogs;
    }

    public MoodLog getMoodLogById(int moodId) throws ClassNotFoundException {
        String selectQuery = "SELECT moodId, username, date, mood, rating, description FROM moodlog WHERE moodId = ?";
        try (Connection connection = DatabaseHandler.connectToDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setInt(1, moodId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String username = resultSet.getString("username");
                Date date = resultSet.getDate("date");
                String mood = resultSet.getString("mood");
                int rating = resultSet.getInt("rating");
                String description = resultSet.getString("description");

                return new MoodLog(moodId, username, date, mood, rating, description);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving mood log: " + e.getMessage());
        }
        return null;
    }


}


package source.mentalhealthassistant.core;

import source.mentalhealthassistant.Session;

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

    public int getRating() {
        return rating;
    }

    public Date getDate() {
        return date;
    }

    public int getMoodId() {
        return moodId;
    }

    public String getDescription() {
        return description;
    }

    public String getMood() {
        return mood;
    }

    public String getUsername() {
        return username;
    }

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

    public static void saveMoodToDatabase(String username,String mood, int rating, String description) throws ClassNotFoundException {
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


}


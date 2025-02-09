package source.mentalhealthassistant.core;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import source.mentalhealthassistant.MoodTrackerController;
import source.mentalhealthassistant.Session;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mentalhealth";
    private static final String USER = "root";
    private static final String PASSWORD = "Abbas@mysql23#*";
//private static final String PASSWORD = "castaway110";
    public static Connection connectToDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void saveReminder(Reminder reminder, String username) throws ClassNotFoundException {
        String sql = "INSERT INTO Reminder (reminderId, username, message, time, isRecurring) VALUES (?, ?, ?, ?, ?)";
        boolean userExists = doesUsernameExist(username);
        if (!userExists) {
            System.out.println("User does not exist. Please create an account first.");
            return;
        }

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reminder.getReminderId());
            stmt.setString(2, username); // Use username instead of userId
            stmt.setString(3, reminder.getMessage());
            stmt.setTimestamp(4, Timestamp.valueOf(reminder.getTime()));
            stmt.setBoolean(5, reminder.isRecurring());

            stmt.executeUpdate();
            System.out.println("Reminder saved successfully.");
            reminder.scheduleReminder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesUsernameExist(String username) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM User WHERE username = ?";
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int createConversation(String userName, int chatbotId, String name) throws ClassNotFoundException {
        String sql = "INSERT INTO conversation (username, chatbot_id, name) VALUES (?, ?, ?)";
        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, userName);
            stmt.setInt(2, chatbotId);
            stmt.setString(3, name);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return the newly created conversation_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if failed
    }

    public static void deleteConversation(int conversationId) throws ClassNotFoundException {
        String deleteMessagesQuery = "DELETE FROM message WHERE conversation_id = ?";
        String deleteConversationQuery = "DELETE FROM conversation WHERE conversation_id = ?";

        try (Connection conn = connectToDatabase()) {
            conn.setAutoCommit(false); // Start transaction

            // Delete messages first
            try (PreparedStatement stmt1 = conn.prepareStatement(deleteMessagesQuery)) {
                stmt1.setInt(1, conversationId);
                stmt1.executeUpdate();
            }

            // Delete conversation
            try (PreparedStatement stmt2 = conn.prepareStatement(deleteConversationQuery)) {
                stmt2.setInt(1, conversationId);
                stmt2.executeUpdate();
            }

            conn.commit(); // Commit transaction
            System.out.println("Conversation and related messages deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMessage(int conversationId, String content, int senderId, String senderName) throws ClassNotFoundException {
        String query = "INSERT INTO message (message_id, conversation_id, content, sender_id, sender_name, timestamp) VALUES (?, ?, ?, ?, ?, NOW())";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            String messageId = UUID.randomUUID().toString(); // Generate UUID in Java

            stmt.setString(1, messageId);
            stmt.setInt(2, conversationId);
            stmt.setString(3, content);
            stmt.setInt(4, senderId);
            stmt.setString(5, senderName);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Message added successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Conversation> getConversationsByUsername(String username) throws ClassNotFoundException {
        String sql = "SELECT * FROM conversation WHERE username = ?";
        List<Conversation> conversations = new ArrayList<>();

        try (Connection conn = connectToDatabase();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                conversations.add(new Conversation(
                        resultSet.getInt("conversation_id"),
                        resultSet.getString("username"),
                        resultSet.getInt("chatbot_id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conversations;
    }

    public static List<Message> getMessagesByConversationId(int conversationId) throws ClassNotFoundException {
        String sql = "SELECT * FROM message WHERE conversation_id = ? ORDER BY timestamp ASC";
        List<Message> messages = new ArrayList<>();
        try (Connection conn = connectToDatabase();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, conversationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getString("message_id"),
                        resultSet.getInt("conversation_id"),
                        resultSet.getString("content"),
                        resultSet.getInt("sender_id"),
                        resultSet.getString("sender_name"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public static Map<String, Integer> getMoodData(String userId) {
        Map<String, Integer> moodData = new HashMap<>();
        String query = "SELECT mood, COUNT(*) AS moodCount FROM MoodLog WHERE username = ? GROUP BY mood";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String mood = rs.getString("mood");
                int count = rs.getInt("moodCount");
                moodData.put(mood, count);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return moodData;
    }


}

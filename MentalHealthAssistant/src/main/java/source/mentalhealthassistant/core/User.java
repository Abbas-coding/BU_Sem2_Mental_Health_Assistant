package source.mentalhealthassistant.core;

import java.sql.*;

public class User {
    private String userId;
    private String name;
    private int age;
    private String password;
    private String email;
    private String profilePreference;
    private int supportNetworkId;

    private static DatabaseHandler dbHandler = new DatabaseHandler(); // Shared DatabaseHandler instance

    public User(String userId, String profilePreference, String password, int age, String email, int supportNetworkId) {
        this.userId = userId;
        this.name = userId; // Assuming 'name' is the same as 'userId' (adjust as needed)
        this.age = age;
        this.password = password;
        this.email = email;
        this.profilePreference = profilePreference;
        this.supportNetworkId = supportNetworkId;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePreference() {
        return profilePreference;
    }

    public int getSupportNetworkId() {
        return supportNetworkId;
    }

    // Save user to the database
    public void saveToDatabase() {
        String query = "INSERT INTO User (userId, name, age, password, email, profilePreference, supportNetworkId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = dbHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, userId); // Set userId
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, password);
            statement.setString(5, email);
            statement.setString(6, profilePreference);
            statement.setInt(7, supportNetworkId);
            statement.executeUpdate();
            System.out.println("User saved to database!");
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    // Check if userId already exists
    public static boolean isUserIdTaken(String userId) {
        String query = "SELECT * FROM User WHERE userId = ?";
        try (PreparedStatement statement = dbHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If a row is returned, userId is already taken
        } catch (SQLException e) {
            System.out.println("Error checking userId: " + e.getMessage());
        }
        return false;
    }

    // Load user from the database using userId and password
    public static User findUser(String userId, String password) {
        String query = "SELECT * FROM User WHERE userId = ? AND password = ?";
        try (PreparedStatement statement = dbHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String profilePreference = resultSet.getString("profilePreference");
                int supportNetworkId = resultSet.getInt("supportNetworkId");
                return new User(userId, profilePreference, password, age, email, supportNetworkId);
            }
        } catch (SQLException e) {
            System.out.println("Error loading user: " + e.getMessage());
        }
        return null;
    }

    // Find user by email and age for password recovery
    public static User findUserByEmailAndAge(String email, int age) {
        String query = "SELECT * FROM User WHERE email = ? AND age = ?";
        try (PreparedStatement statement = dbHandler.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            statement.setInt(2, age);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String userId = resultSet.getString("userId");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String profilePreference = resultSet.getString("profilePreference");
                int supportNetworkId = resultSet.getInt("supportNetworkId");
                return new User(userId, profilePreference, password, age, email, supportNetworkId);
            }
        } catch (SQLException e) {
            System.out.println("Error loading user by email and age: " + e.getMessage());
        }
        return null;
    }

    // Other methods as required...
}

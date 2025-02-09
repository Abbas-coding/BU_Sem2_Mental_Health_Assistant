package source.mentalhealthassistant.core;

import java.sql.*;

public class User {
    private int userId;
    private String name;
    private int age;
    private String password;
    private String email;
    private String username;


    public User(String username, String password, int age, String email, String name) {
         // Assuming 'name' is the same as 'userId' (adjust as needed)
        this.age = age;
        this.password = password;
        this.email = email;
        this.username = username;
        this.name = name;
    }
    public User(int id, String username, String password, int age, String email, String name) {
        this.userId = id;// Assuming 'name' is the same as 'userId' (adjust as needed)
        this.age = age;
        this.password = password;
        this.email = email;
        this.username = username;
        this.name = name;
    }

    // Getter methods
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername() {
        return username;
    }


    // Save user to the database
    public void saveToDatabase() throws ClassNotFoundException {
        String query = "INSERT INTO User ( name, age, password, email, username) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseHandler.connectToDatabase().prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, username);

            statement.executeUpdate();
            System.out.println("User saved to database!");
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static User findUserByUsername(String username) throws ClassNotFoundException {
        String query = "SELECT * FROM User WHERE username = ?";
        try (PreparedStatement statement = DatabaseHandler.connectToDatabase().prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");

                return new User(username, password, age, email, name);
            }
        } catch (SQLException e) {
            System.out.println("Error loading user: " + e.getMessage());
        }
        return null;

    }

    // Load user from the database using userId and password
    public static User findUser(String username, String password) throws ClassNotFoundException {
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";
        try (PreparedStatement statement = DatabaseHandler.connectToDatabase().prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("userId");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");

                return new User(id, username, password, age, email, name);
            }
        } catch (SQLException e) {
            System.out.println("Error loading user: " + e.getMessage());
        }
        return null;
    }

    // Find user by email and age for password recovery
    public static User findUserByEmail(String email) throws ClassNotFoundException {
        String query = "SELECT * FROM User WHERE email = ?";
        try (PreparedStatement statement = DatabaseHandler.connectToDatabase().prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                int age = resultSet.getInt("age");
                return new User(username, password, age, email, name);
            }
        } catch (SQLException e) {
            System.out.println("Error loading user by email and age: " + e.getMessage());
        }
        return null;
    }

    public static User updatePassword(String email, String password) throws ClassNotFoundException {
        String query = "UPDATE User SET password = ? WHERE email = ?";
        try (PreparedStatement statement = DatabaseHandler.connectToDatabase().prepareStatement(query)) {
            statement.setString(1, password);
            statement.setString(2, email);
            statement.executeUpdate();
            return findUserByEmail(email);
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
        return null;
        // Other methods as required...
    }
}
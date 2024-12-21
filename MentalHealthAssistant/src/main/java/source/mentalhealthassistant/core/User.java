package source.mentalhealthassistant.core;

import java.util.ArrayList;
import java.util.List;

//public class User {
//    private String userId;
//    private String password;
//    private String email;
//    private String name;
//    private int age;
//    private String profilePreference;
//    private SupportNetwork supportNetwork;
//    private ArrayList<MoodLog> moodLogs;
//    private ArrayList<Reminder> reminders;
//
//    public User(String userId, String password, String email, String name, int age, String profilePreference) {
//        this.userId = userId;
//        this.password = password;
//        this.email = email;
//        this.name = name;
//        this.age = age;
//        this.profilePreference = profilePreference;
//        this.supportNetwork = new SupportNetwork();
//        this.moodLogs = new ArrayList<MoodLog>();
//        this.reminders = new ArrayList<Reminder>();
//    }
//    public String getUserId() {
//        return userId;
//    }
//    public String getPassword() {
//        return password;
//    }
//    public String getEmail() {
//        return email;
//    }
//    public String getName() {
//        return name;
//    }
//    public int getAge() {
//        return age;
//    }
//    public String getProfilePreference() {
//        return profilePreference;
//    }
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//    public void setProfilePreference(String profilePreference) {
//        this.profilePreference = profilePreference;
//    }
//    public void updateProfile() {
//        //TODO
//    }
//    public void viewProgress() {
//        //TODO
//    }
//    public void setReminder() {
//        //TODO
//    }
//    public void addMoodLog() {
//        //TODO
//    }
////    public List<MoodLog> viewMoodLogs() {
////        //TODO
////        return null;
////    }
//}



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static final String FILE_PATH = "users.txt"; // File to store user data

    private String name;
    private int age;
    private String password;
    private String email;

    public User(String name, int age, String password, String email) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.email = email;
    }

    // Getter methods
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

    // Save user data to a file
    public static void saveUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.name + "," + user.age + "," + user.password + "," + user.email);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    // Load all users from the file
    public static List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { // Updated for 4 fields (name, age, password, email)
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    String password = parts[2];
                    String email = parts[3];
                    users.add(new User(name, age, password, email));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing user data found. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
        return users;
    }

    // Find user by username and password
    public static User findUser(String username, String password) {
        List<User> users = loadUsersFromFile();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Find user by email and age
    public static User findUserByEmailAndAge(String email, int age) {
        List<User> users = loadUsersFromFile();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getAge() == age) {
                return user;
            }
        }
        return null;
    }

    // Check if username is already taken
    public static boolean isUsernameTaken(String username) {
        List<User> users = loadUsersFromFile();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
}


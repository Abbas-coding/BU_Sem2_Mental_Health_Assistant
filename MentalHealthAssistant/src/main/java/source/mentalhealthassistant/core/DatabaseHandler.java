package source.mentalhealthassistant.core;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mentalhealth";
    private static final String USER = "root";
//    private static final String PASSWORD = "Abbas@mysql23#*";
private static final String PASSWORD = "castaway110";
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

    public boolean saveUserProgress(User user){
        // To be implemented later
        return false;
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

    public static void updateReminder(Reminder reminder) throws ClassNotFoundException{
        String sql = "UPDATE Reminder SET message = ?, time = ?, isRecurring = ? WHERE reminderId = ?";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reminder.getMessage());
            stmt.setTimestamp(2, Timestamp.valueOf(reminder.getTime()));
            stmt.setBoolean(3, reminder.isRecurring());
            stmt.setString(4, reminder.getReminderId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reminder updated successfully.");
            } else {
                System.out.println("No reminder found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteReminder(String reminderId) throws ClassNotFoundException {
        String sql = "DELETE FROM Reminder WHERE reminderId = ?";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reminderId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Reminder deleted successfully.");
            } else {
                System.out.println("No reminder found with the given ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve Reminder by ID
    public static Reminder getReminderById(String reminderId) throws ClassNotFoundException {
        String sql = "SELECT * FROM Reminder WHERE reminderId = ?";

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, reminderId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String message = rs.getString("message");
                LocalDateTime time = rs.getTimestamp("time").toLocalDateTime();
                boolean isRecurring = rs.getBoolean("isRecurring");

                // Decide the type of reminder
                Reminder reminder;
                if (isRecurring) {
                    // Check recurrence type: Daily or Weekly
                    if (Duration.between(LocalDateTime.now(), time).toDays() == 7) {
                        reminder = new WeeklyReminder(reminderId, message, time);
                    } else {
                        reminder = new DailyReminder(reminderId, message, time);
                    }
                } else {
                    reminder = new EventReminder(reminderId, message, time);
                }

                return reminder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve All Reminders for a User
    public static List<Reminder> getRemindersByUser(String username) throws ClassNotFoundException {
        boolean doesUserExist = doesUsernameExist(username);
        if (!doesUserExist) {
            System.out.println("User does not exist. Please create an account first.");
            return new ArrayList<>();
        }

        String sql = "SELECT * FROM Reminder WHERE username = ?";
        List<Reminder> reminders = new ArrayList<>();

        try (Connection conn = connectToDatabase();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username); // Use username instead of userId

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String reminderId = rs.getString("reminderId");
                String message = rs.getString("message");
                LocalDateTime time = rs.getTimestamp("time").toLocalDateTime();
                boolean isRecurring = rs.getBoolean("isRecurring");

                // Decide the type of reminder
                Reminder reminder;
                if (isRecurring) {
                    // Check recurrence type: Daily or Weekly
                    if (Duration.between(LocalDateTime.now(), time).toDays() == 7) {
                        reminder = new WeeklyReminder(reminderId, message, time);
                    } else {
                        reminder = new DailyReminder(reminderId, message, time);
                    }
                } else {
                    reminder = new EventReminder(reminderId, message, time);
                }

                reminders.add(reminder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reminders;
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



    public User retrieveUserProgress(int userId){
        // To be implemented later
        return null;
    }
}

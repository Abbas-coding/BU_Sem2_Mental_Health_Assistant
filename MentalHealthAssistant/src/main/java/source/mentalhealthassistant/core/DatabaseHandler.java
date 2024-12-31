package source.mentalhealthassistant.core;
import java.sql.*;


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

}

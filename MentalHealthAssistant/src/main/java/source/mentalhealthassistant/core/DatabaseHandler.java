package source.mentalhealthassistant.core;
import java.sql.*;

public class DatabaseHandler {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mentalhealth";
    private static final String USER = "root";
    private static final String PASSWORD = "Abbas@mysql23#*";

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

    public User retrieveUserProgress(int userId){
        // To be implemented later
        return null;
    }
}

package source.mentalhealthassistant;

public class Session {
    private static Session instance;
    private String username;

    private Session() {
        // Private constructor to prevent instantiation
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void clearSession() {
        this.username = null; // Clear session when user logs out
    }
}


package source.mentalhealthassistant.core;

public class DatabaseException extends ExceptionHandling{
    private String query;

    public DatabaseException(String errorMessage, String query) {
        super(errorMessage);
        this.query = query;
    }

    public String getDatabaseQueryDetails() {
        // To be implemented later
        return "";
    }
}

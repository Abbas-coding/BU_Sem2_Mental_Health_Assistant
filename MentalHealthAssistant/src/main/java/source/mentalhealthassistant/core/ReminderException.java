package source.mentalhealthassistant.core;

public class ReminderException extends ExceptionHandling{
    private String reminderType;

    public ReminderException(String errorMessage, String reminderType) {
        super(errorMessage);
        this.reminderType = reminderType;
    }

    @Override
    public String getErrorDetails() {
        // To be implemented later
        return "";
    }

}

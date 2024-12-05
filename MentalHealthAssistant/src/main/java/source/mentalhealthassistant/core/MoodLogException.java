package source.mentalhealthassistant.core;

public class MoodLogException extends ExceptionHandling{
    private String moodType;

    public MoodLogException(String errorMessage, String moodType) {
        super(errorMessage);
        this.moodType = moodType;
    }

    @Override
    public String getErrorDetails() {
        // To be implemented later
        return "";
    }

}

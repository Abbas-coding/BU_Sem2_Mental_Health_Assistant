package source.mentalhealthassistant.core;

import java.time.LocalDateTime;

public class MoodLog {
    private String moodType;
    private int rating;
    private String description;
    private LocalDateTime timestamp;

    public MoodLog(String moodType, int rating, String description, LocalDateTime timestamp) {
        this.moodType = moodType;
        this.rating = rating;
        this.description = description;
        this.timestamp = timestamp;
    }
    public String getMoodType() {
        return moodType;
    }
    public int getRating() {
        return rating;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setMoodType(String moodType) {
        this.moodType = moodType;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

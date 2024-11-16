package source.mentalhealthassistant.core;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String password;
    private String email;
    private String name;
    private int age;
    private String profilePreference;
    //private SupportNetwork supportNetwork;
    //private ArrayList<MoodLog> moodLogs;
    //private ArrayList<Reminder> reminders;

    public User(String userId, String password, String email, String name, int age, String profilePreference) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
        this.profilePreference = profilePreference;
        //this.supportNetwork = new SupportNetwork();
        //this.moodLogs = new ArrayList<MoodLog>();
        //this.reminders = new ArrayList<Reminder>();
    }
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getProfilePreference() {
        return profilePreference;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setProfilePreference(String profilePreference) {
        this.profilePreference = profilePreference;
    }
    public void updateProfile() {
        //TODO
    }
    public void viewProgress() {
        //TODO
    }
    public void setReminder() {
        //TODO
    }
    public void addMoodLog() {
        //TODO
    }
//    public List<MoodLog> viewMoodLogs() {
//        //TODO
//        return null;
//    }
}
